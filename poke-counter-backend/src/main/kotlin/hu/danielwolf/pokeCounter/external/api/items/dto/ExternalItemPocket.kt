package hu.danielwolf.pokeCounter.external.api.items.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource

data class ExternalItemPocket(
    val id: Int,
    val name: String,
    val categories: List<NamedAPIResource>,
    val names: List<ExternalName>,
)