@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.games.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalVersionGroup(
  val id: Int,
  val name: String,
  val order: Int,
  val generation: NamedAPIResource,
  @param:JsonProperty("move_learn_methods") val moveLearnMethods: List<NamedAPIResource>,
  val pokedexes: List<NamedAPIResource>,
  val regions: List<NamedAPIResource>,
  val versions: List<NamedAPIResource>,
)