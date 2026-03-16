@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.service.pokemon

import hu.danielwolf.pokeCounter.domain.model.games.Generation
import hu.danielwolf.pokeCounter.domain.model.pokemon.Ability
import hu.danielwolf.pokeCounter.domain.service.games.GenerationService
import hu.danielwolf.pokeCounter.domain.service.pokemon.AbilityService
import hu.danielwolf.pokeCounter.pokeApi.api.pokemon.PokemonApi
import hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto.ExternalAbility
import hu.danielwolf.pokeCounter.pokeApi.config.toEntityMap
import hu.danielwolf.pokeCounter.pokeApi.config.toURI
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class AbilitySyncService(
    private val pokemonApi: PokemonApi,
    private val abilityService: AbilityService,
    private val generationService: GenerationService,
    private val logger: Logger
) {

    fun syncAll() {
        logger.info("Starting ability sync...")
        val summaries = pokemonApi.getAllAbilities(0, 500)
        summaries.results.forEach {
            val external = pokemonApi.followAbility(it.url.toURI())
            abilityService.save(external.toEntity(generationService.getByName(external.generation.name)))
        }
        logger.info("Finished ability sync.")
    }
}

fun ExternalAbility.toEntity(generation: Generation): Ability = Ability(
    id = this.id,
    name = this.name,
    isMainSeries = this.isMainSeries,
    generation = generation,
    names = this.names.toEntityMap(),
    flavorTexts = this.flavorTextEntries.groupBy { it.language.name }.mapValues { it.value.first().flavorText }
)
