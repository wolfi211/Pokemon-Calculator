package hu.danielwolf.pokeCounter.external.api.locations.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalRegion(
    val id: Int,
    val locations: List<NamedAPIResource>,
    val name: String,
    val names: List<ExternalName>,
    val mainGeneration: NamedAPIResource,
    val pokedexes: List<NamedAPIResource>,
    val versionGroups: List<NamedAPIResource>,
)