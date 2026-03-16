package hu.danielwolf.pokeCounter.external.api.berries.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalBerryFlavor(
    val id: Int,
    val name: String,
    val berries: List<ExternalFlavorBerryMap>,
    @JsonProperty("contest_type") val contestType: NamedAPIResource,
    val names: List<ExternalName>,
)

data class ExternalFlavorBerryMap(
    val potency: Int,
    val berry: NamedAPIResource,
)