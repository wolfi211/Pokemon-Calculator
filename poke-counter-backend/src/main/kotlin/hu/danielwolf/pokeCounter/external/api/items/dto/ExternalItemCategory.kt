package hu.danielwolf.pokeCounter.external.api.items.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource

data class ExternalItemCategory(
    val id: Int,
    val name: String,
    val items: List<NamedAPIResource>,
    val names: List<ExternalName>,
    val pocket: NamedAPIResource,
)