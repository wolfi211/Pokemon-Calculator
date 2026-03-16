package hu.danielwolf.pokeCounter.external.api.games.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalVersion(
    val id: Int,
    val name: String,
    val names: List<ExternalName>,
    @JsonProperty("version_group") val versionGroup: NamedAPIResource,
)