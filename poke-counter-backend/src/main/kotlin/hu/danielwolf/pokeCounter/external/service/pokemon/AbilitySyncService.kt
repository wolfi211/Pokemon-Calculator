package hu.danielwolf.pokeCounter.external.service.pokemon

import hu.danielwolf.pokeCounter.domain.entities.Ability
import hu.danielwolf.pokeCounter.domain.entities.Generation
import hu.danielwolf.pokeCounter.domain.services.AbilityService
import hu.danielwolf.pokeCounter.domain.services.GenerationService
import hu.danielwolf.pokeCounter.external.api.pokemon.PokemonApi
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalAbility
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import hu.danielwolf.pokeCounter.external.api.utilities.dto.PageRequest
import hu.danielwolf.pokeCounter.external.config.toEntityMap
import hu.danielwolf.pokeCounter.external.config.toURI
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
        val summaries = pokemonApi.getAllAbilities(PageRequest(0, 500))
        summaries.results.forEach {
            try {
                val external = pokemonApi.followAbility(it.url.toURI())
                abilityService.save(external.toEntity(generationService.getByName(external.generation.name)))
            } catch (e: Exception) {
                logger.error("Error syncing ability ${it.name}: ${e.message}")
            }
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
