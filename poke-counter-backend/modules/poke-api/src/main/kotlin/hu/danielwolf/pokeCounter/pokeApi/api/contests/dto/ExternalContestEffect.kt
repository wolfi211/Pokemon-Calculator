@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.contests.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalEffect
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalFlavorText

data class ExternalContestEffect(
  val id: Int,
  val appeal: Int,
  val jam: Int,
  @param:JsonProperty("effect_entries") val effectEntries: List<ExternalEffect>,
  @param:JsonProperty("flavor_text_entries") val flavorTextEntries: List<ExternalFlavorText>,
)