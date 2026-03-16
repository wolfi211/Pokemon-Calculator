@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.berries.dto

import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalBerryFirmness(
  val id: Int,
  val name: String,
  val berries: List<NamedAPIResource>,
  val names: List<ExternalName>,
)