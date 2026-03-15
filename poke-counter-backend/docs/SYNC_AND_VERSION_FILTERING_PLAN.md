# PokeAPI Sync and Version/Version-Group Filtering — Plan

This document captures the agreed sync order, data model changes, and business rules for the backend sync and for filtering Pokémon by game version/version-group.

---

## 1. Current vs past row rule (join tables with version_group or generation)

- **Current / most recent**: Row has **null** `version_group` or **null** `generation`.
- **Past**: Row has `version_group` or `generation` set — meaning “this row applies to **that** version group/generation **and older**.” PokeAPI only declares the generation/version_group where the change was **introduced**; we do not duplicate rows for every generation (lighter DB, smarter query at read time).
- **Querying**: When you need the value for a specific generation or version group, use the **closest higher** row (e.g. among rows with `generation >= requested`, take the one with the smallest such generation; same idea for version_group ordering).

Applies to: **TypeRelation** (generation), **MoveType** (version_group), **PokemonType** (generation), **PokemonAbility** (generation), **PokemonStat** (generation). **PokemonMove** always has `version_group` set from API; use closest higher when querying by version group.

---

## 2. Schema migrations (to implement)

### 2.1 PokedexPokemon

- **Remove**: `species_id`.
- **Add**: `version_group_id` INTEGER NOT NULL REFERENCES version_groups(id), `pokemon_id` INTEGER NOT NULL REFERENCES pokemon(id).
- **Primary key**: `(pokedex_id, version_group_id, pokemon_id)`.
- **Keep**: `entry_number` (dex slot; not an identifier; same entry_number can repeat for multiple Pokémon in the same slot, e.g. seasonal forms or base + Mega/Gmax).

**Entity**: PokedexPokemonId(pokedexId, versionGroupId, pokemonId); PokedexPokemon has pokedex, versionGroup, pokemon, entryNumber. Remove species from entity; Species no longer has pokedexEntries (infer via Pokemon → Species).

### 2.2 Nullable generation for “current” rows (PokemonType, PokemonStat)

- **pokemon_types**: Make `generation_id` nullable. Add partial unique index so at most one “current” row per (pokemon_id, slot): `UNIQUE (pokemon_id, slot) WHERE generation_id IS NULL`.
- **pokemon_stats**: Make `generation_id` nullable. Add partial unique index so at most one “current” row per (pokemon_id, stat_id): `UNIQUE (pokemon_id, stat_id) WHERE generation_id IS NULL`.

**Entities**: PokemonTypeId.generationId → Int?; PokemonType.generation → Generation?; PokemonStat.generation → Generation?.

### 2.3 Optional: pokemon_form_version_group

- If form–version-group many-to-many is used for derivation, add table `(form_id, version_group_id)` and entity. Otherwise form.version_group on PokemonForm is sufficient.

---

## 3. Sync order (four phases)

### Phase 1 — Foundational (in order)

1. Generations  
2. Regions  
3. Types  
4. Damage classes  

**Types — two-phase (no second API pass)**  
- First pass: sync all types from API; persist each Type; keep each **ExternalType** in an in-memory list.  
- Second pass: do **not** call PokeAPI again; iterate the stored ExternalType list and build **TypeRelation** from `damageRelations` (store with **generation = null**) and `pastDamageRelations` (store with that generation).  

---

### Phase 2 — Game structure and join tables

1. VersionGroup  
2. Version  
3. Pokedex  
4. **PokedexVersionGroup** — fill at this point (from ExternalPokedex.versionGroups or ExternalVersionGroup.pokedexes).  
5. MoveLearnMethod  
6. **LearnMethodVersionGroup** — fill at this point (from ExternalLearnMethod.versionGroups).  

**PokedexPokemon**: Do **not** fill yet. Store each **ExternalPokedex** (with pokemonEntries and versionGroups) in an in-memory list for use after Phase 4.

---

### Phase 3 — Abilities and moves

1. Abilities  
2. Moves (with DamageClass and Generation)  
3. **MoveType** — for each move: one row with **version_group = null** (current type/power/pp/etc.); for each entry in ExternalMove.pastValues, one row with that **version_group** set. Query with closest higher.

---

### Phase 4 — Pokemon core and join tables

1. Species  
2. Pokemon (from species varieties)  
3. Pokemon forms  

For each Pokemon, fill from ExternalPokemon (same generation/version_group rule where applicable):

- **PokemonType**: Current from `types` → **generation_id = null**. Past from `pastTypes` → generation set. One row per (pokemon, slot, type, generation).  
- **PokemonAbility**: Current from `abilities` → **generation_id = null**. Past from `pastAbilities` → generation set.  
- **PokemonStat**: Current from `stats` → **generation_id = null**. Past from `pastStats` → generation set.  
- **PokemonMove**: From `moves[].versionGroupDetails`; every row has **version_group** set (API gives per–version-group). One row per (pokemon, move, version_group, move_learn_method). Query with closest higher.

---

### PokedexPokemon fill (after Phase 4, no API calls)

Use the stored ExternalPokedex list.

**Pass A — species-based entries**  
- For each (pokedex, version_group) from PokedexVersionGroup (or stored list):  
  - For each species in the pokedex (from pokemonEntries):  
    - Find **all** Pokemon of that species whose form’s **version_group** equals this version_group.  
    - If that set is **non-empty**: insert one **PokedexPokemon** row per such Pokemon, all with the **same** entry_number (e.g. Deerling’s four seasonal forms in Unova).  
    - If that set is **empty**: insert one row for the **default** variety (same entry_number).  

**Pass B — Mega and Gigantamax only**  
- **Not** Dynamax (Dynamax does not change the form).  
- Iterate all PokemonForm; if **isMega** or **form_name == "gmax"**:  
  - Get that form’s version_group.  
  - Get all pokedexes linked to that version_group (PokedexVersionGroup).  
  - For each such pokedex where the **species** of that form’s Pokemon is already in the dex (from Pass A): insert one **PokedexPokemon** row for that Mega/Gmax **Pokemon** with the **same** entry_number as the base variety for that species in that pokedex+version_group.  

---

## 4. Entry number and form semantics

- **entry_number** is the dex slot, not a unique identifier.  
- Same species slot (e.g. base + Mega/Gmax) → **same** entry_number.  
- Multiple forms in one version (e.g. Deerling seasons) → **same** entry_number for all of them.  
- Gigantamax: detect via **form_name == "gmax"** (no separate flag in API). Mega: **isMega** on PokemonForm.

---

## 5. Todo list (implementation checklist)

- [ ] **Schema migration – PokedexPokemon**: Add version_group_id, pokemon_id; remove species_id; PK (pokedex_id, version_group_id, pokemon_id). Update entity and PokedexPokemonId.
- [ ] **Schema migration – nullable generation**: Make generation_id nullable on pokemon_types and pokemon_stats; add partial unique indexes for one current row per (pokemon_id, slot) and (pokemon_id, stat_id). Update PokemonTypeId, PokemonType, PokemonStat.
- [ ] **Phase 1 sync**: Generations, Regions, Types, DamageClasses. Types: two-phase (sync types + store ExternalType list → iterate to fill TypeRelation; current = null generation, past = set).
- [ ] **Phase 2 sync**: VersionGroup, Version, Pokedex; fill PokedexVersionGroup; MoveLearnMethod; fill LearnMethodVersionGroup. Store ExternalPokedex list in memory.
- [ ] **Phase 3 sync**: Abilities, Moves, MoveType (current = null version_group, past from pastValues; closest higher when querying).
- [ ] **Phase 4 sync**: Species, Pokemon, Pokemon forms; fill PokemonType, PokemonAbility, PokemonStat, PokemonMove (generation/version_group rule as above).
- [ ] **PokedexPokemon fill**: After Phase 4, iterate stored ExternalPokedex: Pass A (per pokedex+version_group, per species → all matching forms or default; same entry_number); Pass B (Mega + gmax forms → same entry_number as base).
- [ ] **Orchestrator**: PokeApiSyncService.syncAll() runs Phase 1 → 2 → 3 → 4 → PokedexPokemon fill; pagination as needed.
- [ ] **Phase 1 API**: Filter matchup candidates by version_group using PokedexPokemon (and optional FormVersionGroup if used).

---

## 6. PokeAPI rate limiting

To avoid overloading PokeAPI (no strict documented REST limit; 429s occur with aggressive or parallel requests), all outbound requests go through a **WebClient filter** that enforces a minimum delay between consecutive calls.

- **Config**: `poke-api.min-delay-between-requests-ms` (default **100** ms). Set to **0** to disable.
- **Behaviour**: Before each request, the filter waits so that at least this many milliseconds have passed since the previous request. Sync code is unchanged; throttling is central and automatic.
- **Implementation**: `PokeApiRateLimiter` (in `external.config`) + `ExchangeFilterFunction` on the PokeAPI `WebClient` in `WebclientConfig`.

## 7. Filtering by version/version-group

- “Pokémon available in version group V” = Pokemon that have at least one **PokedexPokemon** row with `version_group_id = V` and `pokedex_id` in the set of pokedexes linked to V via **PokedexVersionGroup**.  
- No need for a separate FormVersionGroup for availability if PokedexPokemon is fully populated as above.
