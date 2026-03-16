@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalPokemonHabitat(
  val id: Int,
  val name: String,
  val names: List<ExternalName>,
  @param:JsonProperty("pokemon_species") val pokemonSpecies: List<NamedAPIResource>
)