package hu.danielwolf.pokeCounter.external.api.move.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalDescription
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalLearnMethod(
    val id: Int,
    val name: String,
    val descriptions: List<ExternalDescription>,
    val names: List<ExternalName>,
    @JsonProperty("version_groups") val versionGroups: List<NamedAPIResource>
)