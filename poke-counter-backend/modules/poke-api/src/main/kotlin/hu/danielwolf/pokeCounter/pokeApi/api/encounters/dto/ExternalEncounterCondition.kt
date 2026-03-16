@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.encounters.dto

import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalEncounterCondition(
  val id: Int,
  val name: String,
  val names: List<ExternalName>,
  val values: List<NamedAPIResource>
)