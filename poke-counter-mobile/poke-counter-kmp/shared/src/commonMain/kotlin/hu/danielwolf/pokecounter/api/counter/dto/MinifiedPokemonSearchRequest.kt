package hu.danielwolf.pokecounter.api.counter.dto

import kotlinx.serialization.Serializable

@Serializable
class MinifiedPokemonSearchRequest {
    var searchText: String? = null
    var versionGroup: String? = null
}
