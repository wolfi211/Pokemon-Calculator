package hu.danielwolf.pokeCounter.external.api.games.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalVersionGroup(
    val id: Int,
    val name: String,
    val order: Int,
    val generation: NamedAPIResource,
    val moveLearnMethods: List<NamedAPIResource>,
    val pokedexes: List<NamedAPIResource>,
    val regions: List<NamedAPIResource>,
    val versions: List<NamedAPIResource>,
)
