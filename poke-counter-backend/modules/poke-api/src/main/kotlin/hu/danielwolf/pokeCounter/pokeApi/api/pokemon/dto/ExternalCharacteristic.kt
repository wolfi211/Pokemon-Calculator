@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto

import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalDescription
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalCharacteristic(
  val id: Int,
  val geneModulo: Int,
  val possibleValues: List<Int>,
  val highestStat: NamedAPIResource,
  val descriptions: List<ExternalDescription>
)