package hu.danielwolf.pokeCounter.external.api.contests.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalEffect
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalFlavorText
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalContestEffect(
    val id: Int,
    val appeal: Int,
    val jam: Int,
    @JsonProperty("effect_entries") val effectEntries: List<ExternalEffect>,
    @JsonProperty("flavor_text_entries") val flavorTextEntries: List<ExternalFlavorText>,
)