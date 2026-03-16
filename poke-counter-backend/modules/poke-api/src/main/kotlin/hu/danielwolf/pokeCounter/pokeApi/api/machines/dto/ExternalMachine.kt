@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.machines.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalMachine(
  val id: Int,
  val item: NamedAPIResource,
  val move: NamedAPIResource,
  @param:JsonProperty("version_group") val versionGroup: NamedAPIResource,
)