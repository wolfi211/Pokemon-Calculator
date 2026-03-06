package hu.danielwolf.pokeCounter.external.api.contests.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalFlavorText
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalSuperContestEffect(
    val id: Int,
    val appeal: Int,
    val flavorTextEntries: List<ExternalFlavorText>,
    val moves: List<NamedAPIResource>,
)
