@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.berries.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalBerryFlavor(
  val id: Int,
  val name: String,
  val berries: List<ExternalFlavorBerryMap>,
  @param:JsonProperty("contest_type") val contestType: NamedAPIResource,
  val names: List<ExternalName>,
)

data class ExternalFlavorBerryMap(
  val potency: Int,
  val berry: NamedAPIResource,
)