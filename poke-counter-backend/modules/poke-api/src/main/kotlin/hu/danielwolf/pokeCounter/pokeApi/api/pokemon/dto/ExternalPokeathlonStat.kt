@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalPokeathlonStat (
  val id: Int,
  val name: String,
  val names: List<ExternalName>,
  @param:JsonProperty("affecting_natures") val affectingNatures: ExternalNaturePokeathlonStatAffectSets,
)

data class ExternalNaturePokeathlonStatAffectSets(
  val increase: List<ExternalNaturePokeathlonStatAffect>,
  val decrease: List<ExternalNaturePokeathlonStatAffect>,
)

data class ExternalNaturePokeathlonStatAffect(
  @param:JsonProperty("max_change") val maxChange: Int,
  val nature: NamedAPIResource,
)