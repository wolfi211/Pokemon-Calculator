package hu.danielwolf.pokeCounter.external.api.pokemon.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalDescription
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalGrowthRate (
    val id: Int,
    val name: String,
    val formula: String,
    val description: List<ExternalDescription>,
    val levels: List<ExternalGrowthRateExperienceLevel>,
    @JsonProperty("pokemon_species") val pokemonSpecies: List<NamedAPIResource>
)

data class ExternalGrowthRateExperienceLevel(
  val level: Int,
  val experience: Int,
)