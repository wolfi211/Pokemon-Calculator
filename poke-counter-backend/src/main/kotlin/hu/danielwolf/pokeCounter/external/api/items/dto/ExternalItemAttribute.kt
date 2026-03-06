package hu.danielwolf.pokeCounter.external.api.items.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalDescription
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalItemAttribute(
    val id: Int,
    val name: String,
    val items: List<NamedAPIResource>,
    val names: List<ExternalName>,
    val descriptions: List<ExternalDescription>,
)
