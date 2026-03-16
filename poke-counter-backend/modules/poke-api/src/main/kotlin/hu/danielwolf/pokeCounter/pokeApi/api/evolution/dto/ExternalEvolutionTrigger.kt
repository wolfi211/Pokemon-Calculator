@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.evolution.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalEvolutionTrigger(
  val id: Int,
  val name: String,
  val names: List<ExternalName>,
  @param:JsonProperty("pokemon_species") val pokemonSpecies: List<NamedAPIResource>
)