package hu.danielwolf.pokeCounter.external.api.games.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalGeneration(
    val id: Int,
    val name: String,
    val abilities: List<NamedAPIResource>,
    val names: List<ExternalName>,
    val mainRegion: NamedAPIResource,
    val moves: List<NamedAPIResource>,
    val pokemonSpecies: List<NamedAPIResource>,
    val types: List<NamedAPIResource>,
    val versionGroups: List<NamedAPIResource>,
)
