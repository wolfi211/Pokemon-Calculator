package hu.danielwolf.pokeCounter.external.api.pokemon.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalDescription
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
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