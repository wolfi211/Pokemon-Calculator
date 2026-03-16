package hu.danielwolf.pokeCounter.external.api.machines.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalMachine(
    val id: Int,
    val item: NamedAPIResource,
    val move: NamedAPIResource,
    @JsonProperty("version_group") val versionGroup: NamedAPIResource,
)