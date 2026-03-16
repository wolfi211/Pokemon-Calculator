package hu.danielwolf.pokeCounter.external.api.berries.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalBerry(
    val id: Int,
    val name: String,
    @JsonProperty("growth_time") val growthTime: Int,
    @JsonProperty("max_harvest") val maxHarvest: Int,
    @JsonProperty("natural_gift_power") val naturalGiftPower: Int,
    val size: Int,
    val smoothness: Int,
    @JsonProperty("soil_dryness") val soilDryness: Int,
    val firmness: NamedAPIResource,
    val flavors: List<ExternalBerryFlavorMap>,
    val item: NamedAPIResource,
    @JsonProperty("natural_gift_type") val naturalGiftType: NamedAPIResource,
)

data class ExternalBerryFlavorMap(
    val potency: Int,
    val flavor: NamedAPIResource,
)