package hu.danielwolf.pokeCounter.external.api.contests.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalContestType(
    val id: Int,
    val name: String,
    @JsonProperty("berry_flavor") val berryFlavor: NamedAPIResource,
    val names: List<ExternalContestName>,
)

data class ExternalContestName(
    val name: String,
    val color: String,
    val language: NamedAPIResource,
)