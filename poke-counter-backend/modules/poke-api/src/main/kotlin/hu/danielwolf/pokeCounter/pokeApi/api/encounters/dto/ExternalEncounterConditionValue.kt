@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.encounters.dto

import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalEncounterConditionValue(
  val id: Int,
  val name: String,
  val condition: NamedAPIResource,
  val names: List<ExternalName>,
)