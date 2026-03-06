package hu.danielwolf.pokeCounter.external.api.dto.pokemon

import hu.danielwolf.pokeCounter.external.api.dto.ExternalDescription
import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalGrowthRate (
  val id: Int,
  val name: String,
  val formula: String,
  val description: List<ExternalDescription>,
  val levels: List<ExternalGrowthRateExperienceLevel>,
  val pokemonSpecies: List<NamedAPIResource>
)

@Serializable
data class ExternalGrowthRateExperienceLevel(
  val level: Int,
  val experience: Int,
)