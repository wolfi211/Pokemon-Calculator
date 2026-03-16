@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.contests.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalContestType(
  val id: Int,
  val name: String,
  @param:JsonProperty("berry_flavor") val berryFlavor: NamedAPIResource,
  val names: List<ExternalContestName>,
)

data class ExternalContestName(
  val name: String,
  val color: String,
  val language: NamedAPIResource,
)