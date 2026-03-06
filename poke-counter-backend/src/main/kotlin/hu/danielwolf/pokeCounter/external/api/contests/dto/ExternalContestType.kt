package hu.danielwolf.pokeCounter.external.api.contests.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalContestType(
    val id: Int,
    val name: String,
    val berryFlavor: NamedAPIResource,
    val names: List<ExternalContestName>,
)

@Serializable
data class ExternalContestName(
    val name: String,
    val color: String,
    val language: NamedAPIResource,
)
