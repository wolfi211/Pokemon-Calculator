package hu.danielwolf.pokeCounter.external.service

import hu.danielwolf.pokeCounter.external.api.games.dto.ExternalPokedex
import hu.danielwolf.pokeCounter.external.service.games.GamesSyncService
import hu.danielwolf.pokeCounter.external.service.move.DamageClassSyncService
import hu.danielwolf.pokeCounter.external.service.move.MoveLearnMethodSyncService
import hu.danielwolf.pokeCounter.external.service.move.MovesSyncService
import hu.danielwolf.pokeCounter.external.service.pokemon.AbilitySyncService
import hu.danielwolf.pokeCounter.external.service.pokemon.PokemonFormSyncService
import hu.danielwolf.pokeCounter.external.service.pokemon.PokemonSyncService
import hu.danielwolf.pokeCounter.external.service.pokemon.SpeciesSyncService
import hu.danielwolf.pokeCounter.external.service.types.TypesSyncService
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class PokeApiSyncService(
    private val gamesSyncService: GamesSyncService,
    private val typesSyncService: TypesSyncService,
    private val damageClassSyncService: DamageClassSyncService,
    private val moveLearnMethodSyncService: MoveLearnMethodSyncService,
    private val abilitySyncService: AbilitySyncService,
    private val movesSyncService: MovesSyncService,
    private val speciesSyncService: SpeciesSyncService,
    private val pokemonSyncService: PokemonSyncService,
    private val pokemonFormSyncService: PokemonFormSyncService,
    private val pokedexPokemonFillService: PokedexPokemonFillService,
    private val logger: Logger
) {

    /**
     * Stored ExternalPokedex list from Phase 2 for later PokedexPokemon fill (after Phase 4).
     */
    var externalPokedexes: List<ExternalPokedex> = emptyList()

    /**
     * Phase 1: Foundational — generations, regions, types (with type relations), damage classes.
     */
    fun syncPhase1() {
        logger.info("PokeAPI sync Phase 1 starting...")
        gamesSyncService.syncAllGenerations()
        gamesSyncService.syncAllRegions()
        typesSyncService.syncAll()
        damageClassSyncService.syncAll()
        logger.info("PokeAPI sync Phase 1 finished.")
    }

    /**
     * Phase 2: Game structure — version groups, versions, pokedexes (with PokedexVersionGroup),
     * move learn methods (with LearnMethodVersionGroup). Stores ExternalPokedex list for PokedexPokemon fill.
     */
    fun syncPhase2() {
        logger.info("PokeAPI sync Phase 2 starting...")
        gamesSyncService.syncAllVersionGroups()
        gamesSyncService.syncAllVersions()
        externalPokedexes = gamesSyncService.syncAllPokedexes()
        moveLearnMethodSyncService.syncAll()
        logger.info("PokeAPI sync Phase 2 finished.")
    }

    /**
     * Phase 3: Abilities, Moves (with MoveType: current = null version_group, past from pastValues).
     */
    fun syncPhase3() {
        logger.info("PokeAPI sync Phase 3 starting...")
        abilitySyncService.syncAll()
        movesSyncService.syncAll()
        logger.info("PokeAPI sync Phase 3 finished.")
    }

    /**
     * Phase 4: Species, Pokemon (with join tables), Pokemon forms.
     */
    fun syncPhase4() {
        logger.info("PokeAPI sync Phase 4 starting...")
        val externalSpecies = speciesSyncService.syncAll()
        pokemonSyncService.syncAll(externalSpecies)
        pokemonFormSyncService.syncAll()
        logger.info("PokeAPI sync Phase 4 finished.")
    }

    /**
     * Fills pokedex_pokemon from stored ExternalPokedex (Pass A: species-based; Pass B: Mega/Gmax).
     * Call after syncPhase4().
     */
    fun fillPokedexPokemon() {
        pokedexPokemonFillService.fill(externalPokedexes)
    }

    /**
     * Full sync: Phase 1 → 2 → 3 → 4 → PokedexPokemon fill.
     */
    fun syncAll() {
        syncPhase1()
        syncPhase2()
        syncPhase3()
        syncPhase4()
        fillPokedexPokemon()
        logger.info("PokeAPI full sync finished.")
    }
}
