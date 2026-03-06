package hu.danielwolf.pokeCounter.external.api.berries.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalBerry(
    val id: Int,
    val name: String,
    val growthTime: Int,
    val maxHarvest: Int,
    val naturalGiftPower: Int,
    val size: Int,
    val smoothness: Int,
    val soilDryness: Int,
    val firmness: NamedAPIResource,
    val flavors: List<ExternalBerryFlavorMap>,
    val item: NamedAPIResource,
    val naturalGiftType: NamedAPIResource,
)

@Serializable
data class ExternalBerryFlavorMap(
    val potency: Int,
    val flavor: NamedAPIResource,
)
