package hu.danielwolf.pokeCounter.external.service

import hu.danielwolf.pokeCounter.external.api.games.dto.ExternalPokedex
import hu.danielwolf.pokeCounter.external.service.games.GamesSyncService
import hu.danielwolf.pokeCounter.external.service.move.DamageClassSyncService
import hu.danielwolf.pokeCounter.external.service.move.MoveLearnMethodSyncService
import hu.danielwolf.pokeCounter.external.service.types.TypesSyncService
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class PokeApiSyncService(
    private val gamesSyncService: GamesSyncService,
    private val typesSyncService: TypesSyncService,
    private val damageClassSyncService: DamageClassSyncService,
    private val moveLearnMethodSyncService: MoveLearnMethodSyncService,
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
}
