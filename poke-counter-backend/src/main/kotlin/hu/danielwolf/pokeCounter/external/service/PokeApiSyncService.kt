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
import hu.danielwolf.pokeCounter.external.service.pokemon.StatSyncService
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
    private val statSyncService: StatSyncService,
    private val logger: Logger
) {

    fun syncAll() {
        logger.info("Starting PokeAPI full sync...")
//
//        gamesSyncService.syncAllGenerations()
//        gamesSyncService.syncAllRegions()
//        typesSyncService.syncAll()
//        damageClassSyncService.syncAll()
//
//        gamesSyncService.syncAllVersionGroups()
//        gamesSyncService.syncAllVersions()
//        val externalPokedexes = gamesSyncService.syncAllPokedexes()
//        moveLearnMethodSyncService.syncAll()
//
//        abilitySyncService.syncAll()
//        movesSyncService.syncAll()
//
//        speciesSyncService.syncAll()
        statSyncService.syncAll()
        pokemonSyncService.syncAll()
        pokemonFormSyncService.syncAll()

//        pokedexPokemonFillService.fill(externalPokedexes)

        logger.info("PokeAPI full sync finished.")
    }
}
