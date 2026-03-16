@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalDescription
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalGrowthRate (
  val id: Int,
  val name: String,
  val formula: String,
  val description: List<ExternalDescription>,
  val levels: List<ExternalGrowthRateExperienceLevel>,
  @param:JsonProperty("pokemon_species") val pokemonSpecies: List<NamedAPIResource>
)

data class ExternalGrowthRateExperienceLevel(
  val level: Int,
  val experience: Int,
)