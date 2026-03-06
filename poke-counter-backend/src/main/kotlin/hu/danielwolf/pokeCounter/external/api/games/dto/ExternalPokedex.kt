package hu.danielwolf.pokeCounter.external.api.games.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalDescription
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalPokedex(
    val id: Int,
    val name: String,
    val isMainSeries: Boolean,
    val descriptions: List<ExternalDescription>,
    val names: List<ExternalName>,
    val pokemonEntries: List<ExternalPokemonEntry>,
    val region: NamedAPIResource,
    val versionGroups: List<NamedAPIResource>,
)

@Serializable
data class ExternalPokemonEntry(
    val entryNumber: Int,
    val pokemonSpecies: NamedAPIResource,
)
