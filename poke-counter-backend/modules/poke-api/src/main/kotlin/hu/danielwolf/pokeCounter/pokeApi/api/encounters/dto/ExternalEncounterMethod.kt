@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.encounters.dto

import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName

data class ExternalEncounterMethod(
  val id: Int,
  val name: String,
  val order: Int,
  val names: List<ExternalName>,
)