package hu.danielwolf.pokeCounter.external.api.evolution.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalEvolutionTrigger(
    val id: Int,
    val name: String,
    val names: List<ExternalName>,
    @JsonProperty("pokemon_species") val pokemonSpecies: List<NamedAPIResource>
)