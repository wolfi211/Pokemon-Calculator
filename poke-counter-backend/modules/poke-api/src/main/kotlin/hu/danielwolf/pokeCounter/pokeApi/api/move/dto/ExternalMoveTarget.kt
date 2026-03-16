@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.move.dto

import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalDescription
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalMoveTarget(
  val id: Int,
  val name: String,
  val descriptions: List<ExternalDescription>,
  val moves: List<NamedAPIResource>,
  val names: List<ExternalName>,
)