package hu.danielwolf.pokeCounter.external.api.items.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalEffect
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalItemFlingEffect(
    val id: Int,
    val name: String,
    val effectEntries: List<ExternalEffect>,
    val items: List<NamedAPIResource>,
)
