@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.move.dto

import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName

data class ExternalMoveBattleStyle(
  val id: Int,
  val name: String,
  val names: List<ExternalName>,
)