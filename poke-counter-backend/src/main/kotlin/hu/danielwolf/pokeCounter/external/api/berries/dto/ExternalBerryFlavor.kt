package hu.danielwolf.pokeCounter.external.api.berries.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalBerryFlavor(
    val id: Int,
    val name: String,
    val berries: List<ExternalFlavorBerryMap>,
    val contestType: NamedAPIResource,
    val names: List<ExternalName>,
)

@Serializable
data class ExternalFlavorBerryMap(
    val potency: Int,
    val berry: NamedAPIResource,
)
