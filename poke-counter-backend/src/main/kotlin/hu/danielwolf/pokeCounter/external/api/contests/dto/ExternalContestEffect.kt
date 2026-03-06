package hu.danielwolf.pokeCounter.external.api.contests.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalEffect
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalFlavorText
import kotlinx.serialization.Serializable

@Serializable
data class ExternalContestEffect(
    val id: Int,
    val appeal: Int,
    val jam: Int,
    val effectEntries: List<ExternalEffect>,
    val flavorTextEntries: List<ExternalFlavorText>,
)
