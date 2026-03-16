@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.items.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalEffect
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalItemFlingEffect(
  val id: Int,
  val name: String,
  @param:JsonProperty("effect_entries") val effectEntries: List<ExternalEffect>,
  val items: List<NamedAPIResource>,
)