Great — Milestone 0 is done, so you already understand the hardest *mobile-specific* part (signing, device deploy, Kotlin/Native compile). Milestone 1 is mostly **backend-style Kotlin + a new UI paradigm**. Your Spring and Vue experience maps cleanly; the new pieces are Compose, KMP source sets, and iOS networking rules.

---

## What Milestone 1 actually is

**Goal:** type `char` → app calls `GET /api/v1/pokemon/minified-search?query=char` → show the JSON results in a scrollable list (`LazyColumn`).

That’s it. No navigation, no move picker, no counter POST yet. You’re proving one vertical slice:

```
TextField → HTTP GET → JSON → UI list
```

Your Vue equivalent is `PokemonSearch.vue` + `pokemonApi.ts` + `client.ts`. Same architecture as the roadmap’s diagram, just without a ViewModel layer for now (you can add that in Milestone 3).

---

## Step 0 — Get the backend reachable from your phone

Before touching Kotlin, validate the network path. This is the #1 trap for physical iPhones.

1. Start backend: `./gradlew :poke-counter:bootRun` in `poke-counter-backend`
2. Sync data if needed: `curl -X POST http://localhost:8080/api/v1/sync`
3. On your **Mac**, confirm: `curl "http://localhost:8080/api/v1/pokemon/minified-search?query=char"`
4. Find your Mac’s LAN IP (same Wi‑Fi as iPhone): e.g. `ipconfig getifaddr en0`
5. On your **iPhone Safari**, open: `http://<mac-ip>:8080/api/v1/pokemon/minified-search?query=char`

If Safari can’t load it, the app won’t either. Fix Wi‑Fi/firewall first.

**Why this matters:** Simulator can use `localhost`. A physical device cannot — `localhost` on the phone means the phone itself, not your Mac.

---

## Step 1 — Understand where code lives (KMP mental model)

Your project uses a `shared` module (roadmap says `composeApp`; same idea). Think of it like a monorepo with platform slices:

| Folder | Analogy for you |
|--------|-----------------|
| `shared/src/commonMain/` | Shared library — like your Vue `src/` + API client |
| `shared/src/iosMain/` | iOS-only glue (entry point, platform helpers) |
| `shared/src/androidMain/` | Android-only glue (later) |
| `iosApp/` | Thin native shell — like `index.html` + Vite config, not your app logic |
| `androidApp/` | Same for Android |

**Rule for Milestone 1:** put almost everything in `commonMain`. You already have `App.kt` there — that’s your root UI, like `App.vue`.

Files you’ll likely create under `commonMain/kotlin/hu/danielwolf/pokecounter/`:

```
data/
  dto/           ← TypeScript interfaces → @Serializable data classes
  api/           ← pokemonApi.ts
  (optional) repository/   ← PokemonService.ts — thin wrapper, optional for M1
ui/
  search/        ← a screen composable (EnemySearchScreen or similar)
```

You do **not** need to touch `iosApp/ContentView.swift` or `MainViewController.kt` unless something breaks — they already host `App()`.

---

## Step 2 — Add dependencies (Gradle, not code yet)

Open:

- `gradle/libs.versions.toml` — version catalog (like a BOM)
- `shared/build.gradle.kts` — where dependencies attach to source sets

You need two libraries (roadmap lists them):

| Library | Why |
|---------|-----|
| **Ktor Client** | HTTP — your `fetch` / `apiGet` |
| **kotlinx.serialization** | JSON ↔ Kotlin data classes — your TypeScript types |

Also add Ktor’s **ContentNegotiation** + **Json** plugins (serialization doesn’t happen automatically).

**Learning exercise:** read Ktor’s “create client” docs and note which artifacts go in `commonMain` vs platform-specific. For iOS you typically need an **engine** (often `ktor-client-darwin` in `iosMain`). That’s the first time you’ll use `expect`/`actual` *or* just declare the engine in the iOS source set’s dependencies block — both are valid; for M1, putting `Darwin` engine in `iosMain.dependencies` is enough.

Apply the **kotlinx.serialization plugin** to the `shared` module (same pattern as `composeCompiler` plugin you already have).

Sync Gradle in Android Studio and fix any sync errors before writing app code.

---

## Step 3 — Model the API contract (DTOs)

Your backend already defines the shape in `PokemonSummaryDto.kt`. Your Vue mirror is `poke-counter-frontend/src/types/api/pokemonSummary.ts`.

For Milestone 1 you have two learning paths:

**A (recommended):** mirror the real DTOs (`PokemonSummaryDto`, nested `MinifiedTypeDto`, `StatBlockDto`, etc.)  
**B (shortcut):** start with `{ id, localizedName }` only to prove the pipe works, then expand

Path A teaches serialization properly and avoids surprises later. Your backend uses `Set<MinifiedTypeDto>` — JSON comes over as an array; in the client, `List` is usually easier than `Set`.

**Where to look:** compare backend Kotlin ↔ frontend TypeScript field names. Jackson/Spring and kotlinx.serialization both expect **matching JSON property names** (camelCase here).

**Checkpoint:** write a tiny `commonTest` that decodes one JSON snippet into your DTO. You already have `shared/src/commonTest/` — this is like a JUnit test for a Jackson `ObjectMapper`, and it runs without a device.

---

## Step 4 — Build the HTTP layer (Ktor client + API function)

Map from your Vue stack:

| Vue | Kotlin (commonMain) |
|-----|---------------------|
| `api/client.ts` → `apiGet` | `HttpClient` configured once with base URL + JSON |
| `api/pokemonApi.ts` → `searchPokemon` | suspend function `searchPokemon(query: String)` |
| `PokemonService.ts` | optional repository wrapping the API call |

**Pattern:**

1. Create/configure `HttpClient` (base URL, JSON content type, logging if you want)
2. `suspend fun searchPokemon(query: String): List<PokemonSummaryDto>`  
   - `GET /api/v1/pokemon/minified-search`  
   - query param `query`  
   - `versionGroup` is optional — skip for M1 unless you need it

You know coroutines from Spring WebFlux / async patterns: the composable will call this from a coroutine scope. No callbacks.

**Important:** create the client in one place (singleton or passed into a repository), not per request.

---

## Step 5 — Solve the base URL problem (simulator vs device)

Vue uses `VITE_API_BASE_URL`. Mobile needs the same idea:

| Target | Base URL |
|--------|----------|
| iOS Simulator | `http://localhost:8080` |
| Physical iPhone | `http://192.168.x.x:8080` (your Mac) |

For Milestone 1, a **hardcoded constant you change when switching** is fine. That teaches the problem before you abstract it.

Later (roadmap): `expect/actual` or build config per target. Don’t over-engineer now.

---

## Step 6 — iOS-specific: allow HTTP to your dev server

Your `iosApp/iosApp/Info.plist` is minimal right now — no App Transport Security (ATS) exception. iOS **blocks plain HTTP** by default.

You must add an ATS exception for your dev host (localhost and/or your LAN IP). Roadmap mentions this; it’s not optional for physical device + `http://`.

This is the main **platform folder** change for M1. Everything else stays in `commonMain`.

---

## Step 7 — Build the UI (Compose, mapped from Vue)

Replace the template “Click me!” flow in `App.kt` with a minimal search screen.

Concept map:

| Vue | Compose |
|-----|---------|
| `ref('')` | `remember { mutableStateOf("") }` |
| `watch(query, …)` | `LaunchedEffect(query)` |
| `:delay="300"` on Multiselect | debounce inside `LaunchedEffect` (e.g. `delay(300)`) |
| `v-if` loading / empty / error | `when (uiState) { Loading, Success, Empty, Error }` |
| list in dropdown | `LazyColumn { items(results) { … } }` |

**Minimal UI for M1:**

1. `TextField` bound to query state
2. On query change → debounce → call `searchPokemon`
3. Show **Loading** while request in flight
4. Show **Error** message if network/parse fails (wrap in try/catch)
5. Show **Empty** if query returned `[]`
6. Show **LazyColumn** of results — for now, `Text(localizedName)` per row is enough (sprites/types come later)

**`LaunchedEffect` is the key learning moment.** It runs a coroutine when its keys change — closest thing to Vue’s `watch` with `{ immediate: true }`. Read what happens when `query` goes from `"char"` → `"chari"` → `"chariz"`: you want to cancel stale requests (Ktor/coroutine cancellation handles much of this if you structure it correctly).

For M1 you can skip ViewModel and keep state in the composable with `remember` + `mutableStateOf`. That’s simpler while learning Compose. Extract a ViewModel in Milestone 3 when state spans screens.

---

## Step 8 — Wire it and run

Suggested order of work (each step should compile/run):

1. Backend curl works from Mac ✓  
2. Gradle deps sync ✓  
3. DTO + JSON unit test passes ✓  
4. API function works (temporarily log results from a test or a dumb button in `App`) ✓  
5. ATS + correct base URL → request succeeds on device ✓  
6. Replace button with TextField + LazyColumn ✓  

Run from Android Studio’s **iOS run configuration** (same as Milestone 0). First run after adding Ktor/serialization may take a while — normal.

---

## Step 9 — How to debug when it breaks

| Symptom | Likely cause |
|---------|----------------|
| “Connection refused” / timeout on device | Wrong base URL (still `localhost`), or backend not bound to `0.0.0.0`, or firewall |
| ATS / “secure connection” error | Missing `Info.plist` exception |
| HTTP 200 but crash in app | DTO mismatch — field missing or wrong type; compare JSON in Safari vs your `@Serializable` classes |
| Empty list | Query too short? Vue uses `:min-chars="2"`. Backend accepts any query — check what you send |
| Gradle sync fails on iOS engine | Wrong Ktor engine dependency for `iosMain` |

Use **Ktor logging** or println in dev. Network Inspector doesn’t exist on iOS like Chrome DevTools — Safari test + logging is your friend.

---

## What to deliberately *not* do yet

Keeps Milestone 1 focused:

- Navigation Compose (Milestone 2)
- ViewModel + StateFlow (Milestone 3 — optional skip for M1)
- Debounced autocomplete UX polish (full-screen search sheet — later)
- Coil/sprites, type chips, theming
- Android emulator / cleartext config
- Sharing DTOs with the Spring backend module

---

## Recommended reading (in order)

1. [KMP project structure](https://kotlinlang.org/docs/multiplatform-discover-project.html) — source sets  
2. [Ktor client — multiplatform](https://ktor.io/docs/client-create-multiplatform.html) — engines per target  
3. [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) — `@Serializable`  
4. Compose: **`LazyColumn`**, **`LaunchedEffect`**, **`remember`** — Android docs apply in CMP  
5. Skim your own Vue files as the spec: `client.ts`, `pokemonApi.ts`, `PokemonSearch.vue`

---

## Your first “where do I click?” session

If you sit down for 2–3 hours, I’d do exactly this:

1. Terminal: backend up, curl + Safari on phone verified  
2. Android Studio: open `shared/build.gradle.kts`, add Ktor + serialization, sync  
3. Create `data/dto/PokemonSummaryDto.kt` (and nested types) from backend/frontend types  
4. Write one `commonTest` JSON decode  
5. Create `data/api/PokeApi.kt` (or `PokemonApi.kt`) with suspend search function  
6. Edit `Info.plist` for ATS; set base URL to Mac IP  
7. Replace `App.kt` body with TextField + list; use `LaunchedEffect`  
8. Deploy to iPhone, type `char`, see Charmander et al.

---

## How this connects to Milestone 2

Once the list works, Milestone 2 wraps it in navigation: tap a row → pass `pokemonId` to move picker screen. The search screen you build now becomes the first route in a `NavHost`. Same composable, plus navigation args — no throwaway work.

---

If you want to go deeper on one slice next — Gradle/Ktor setup, serialization against your real JSON, Compose state flow, or iOS ATS — say which one and we can walk through it interactively (still teaching, not paste-ready code).