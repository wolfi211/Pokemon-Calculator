@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.service.pokemon

import hu.danielwolf.pokeCounter.domain.model.pokemon.Species
import hu.danielwolf.pokeCounter.domain.service.pokemon.SpeciesService
import hu.danielwolf.pokeCounter.pokeApi.api.pokemon.PokemonApi
import hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto.ExternalPokemonSpecies
import hu.danielwolf.pokeCounter.pokeApi.config.toEntityMap
import hu.danielwolf.pokeCounter.pokeApi.config.toURI
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class SpeciesSyncService(
    private val pokemonApi: PokemonApi,
    private val speciesService: SpeciesService,
    private val logger: Logger
) {

    fun syncAll() {
        logger.info("Starting species sync...")
        val stored = mutableListOf<ExternalPokemonSpecies>()
        val summaries = pokemonApi.getAllPokemonSpecies(0, 2000)
        summaries.results.forEach {
            val external = pokemonApi.followPokemonSpecies(it.url.toURI())
            speciesService.save(external.toEntity())
            stored.add(external)
        }
        logger.info("Finished species sync (${stored.size} species).")
    }
}

fun ExternalPokemonSpecies.toEntity(): Species = Species(
    id = this.id,
    name = this.name,
    order = this.order,
    genderRate = this.genderRate,
    captureRate = this.captureRate,
    baseHappiness = this.baseHappiness,
    isBaby = this.isBaby,
    isLegendary = this.isLegendary,
    isMythical = this.isMythical,
    hatchCounter = this.hatchCounter,
    hasGenderDifferences = this.hasGenderDifferences,
    formsSwitchable = this.formsSwitchable,
    names = this.names.toEntityMap(),
    flavorTextEntries = this.flavorTextEntries.groupBy { it.language.name }.mapValues { it.value.first().flavorText },
    formDescriptions = this.formDescriptions.associate { it.language.name to it.description }
)
