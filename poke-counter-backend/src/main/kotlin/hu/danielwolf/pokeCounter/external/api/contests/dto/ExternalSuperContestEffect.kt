package hu.danielwolf.pokeCounter.external.api.contests.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalFlavorText
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalSuperContestEffect(
    val id: Int,
    val appeal: Int,
    @JsonProperty("flavor_text_entries") val flavorTextEntries: List<ExternalFlavorText>,
    val moves: List<NamedAPIResource>,
)