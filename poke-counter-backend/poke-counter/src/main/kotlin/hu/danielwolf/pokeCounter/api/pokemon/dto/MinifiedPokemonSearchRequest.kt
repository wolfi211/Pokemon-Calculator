package hu.danielwolf.pokeCounter.api.pokemon.dto

import kotlinx.serialization.Serializable

@Serializable
class MinifiedPokemonSearchRequest {
  var searchText: String? = null
  var versionGroup: String? = null
}