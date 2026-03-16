@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto

import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalPokemonShape (
  val id: Int,
  val name: String,
  val awesomeNames: List<ExternalAwesomeName>,
  val names: List<ExternalName>,
  val pokemonSpecies: List<NamedAPIResource>,
)

data class ExternalAwesomeName(
  val awesomeName: String,
  val language: NamedAPIResource,
)