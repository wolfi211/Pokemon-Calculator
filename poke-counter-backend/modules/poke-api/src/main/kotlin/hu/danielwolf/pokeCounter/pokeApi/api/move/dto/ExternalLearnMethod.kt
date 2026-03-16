@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.move.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalDescription
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalLearnMethod(
  val id: Int,
  val name: String,
  val descriptions: List<ExternalDescription>,
  val names: List<ExternalName>,
  @param:JsonProperty("version_groups") val versionGroups: List<NamedAPIResource>
)