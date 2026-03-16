@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.items.dto

import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalItemCategory(
  val id: Int,
  val name: String,
  val items: List<NamedAPIResource>,
  val names: List<ExternalName>,
  val pocket: NamedAPIResource,
)