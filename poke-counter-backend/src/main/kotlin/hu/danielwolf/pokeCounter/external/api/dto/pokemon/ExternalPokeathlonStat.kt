package hu.danielwolf.pokeCounter.external.api.dto.pokemon

import hu.danielwolf.pokeCounter.external.api.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalPokeathlonStat (
  val id: Int,
  val name: String,
  val names: List<ExternalName>,
  val affectingNatures: ExternalNaturePokeathlonStatAffectSets,
)

@Serializable
data class ExternalNaturePokeathlonStatAffectSets(
  val increase: List<ExternalNaturePokeathlonStatAffect>,
  val decrease: List<ExternalNaturePokeathlonStatAffect>,
)

@Serializable
data class ExternalNaturePokeathlonStatAffect(
  val maxChange: Int,
  val nature: NamedAPIResource,
)