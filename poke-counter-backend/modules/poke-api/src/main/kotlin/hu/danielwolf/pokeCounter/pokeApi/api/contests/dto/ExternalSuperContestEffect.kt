@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.contests.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalFlavorText
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalSuperContestEffect(
  val id: Int,
  val appeal: Int,
  @param:JsonProperty("flavor_text_entries") val flavorTextEntries: List<ExternalFlavorText>,
  val moves: List<NamedAPIResource>,
)