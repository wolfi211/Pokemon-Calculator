package hu.danielwolf.pokeCounter.external.api.items.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalEffect
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalItemFlingEffect(
    val id: Int,
    val name: String,
    @JsonProperty("effect_entries") val effectEntries: List<ExternalEffect>,
    val items: List<NamedAPIResource>,
)