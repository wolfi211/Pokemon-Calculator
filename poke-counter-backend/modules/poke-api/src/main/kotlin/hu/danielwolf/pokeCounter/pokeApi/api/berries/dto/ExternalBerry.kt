@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.berries.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalBerry(
  val id: Int,
  val name: String,
  @param:JsonProperty("growth_time") val growthTime: Int,
  @param:JsonProperty("max_harvest") val maxHarvest: Int,
  @param:JsonProperty("natural_gift_power") val naturalGiftPower: Int,
  val size: Int,
  val smoothness: Int,
  @param:JsonProperty("soil_dryness") val soilDryness: Int,
  val firmness: NamedAPIResource,
  val flavors: List<ExternalBerryFlavorMap>,
  val item: NamedAPIResource,
  @param:JsonProperty("natural_gift_type") val naturalGiftType: NamedAPIResource,
)

data class ExternalBerryFlavorMap(
  val potency: Int,
  val flavor: NamedAPIResource,
)