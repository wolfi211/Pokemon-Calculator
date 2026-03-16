package hu.danielwolf.pokeCounter.external.api.games.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalDescription
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalPokedex(
    val id: Int,
    val name: String,
    @JsonProperty("is_main_series") val isMainSeries: Boolean,
    val descriptions: List<ExternalDescription>,
    val names: List<ExternalName>,
    @JsonProperty("pokemon_entries") val pokemonEntries: List<ExternalPokemonEntry>,
    val region: NamedAPIResource?,
    @JsonProperty("version_groups") val versionGroups: List<NamedAPIResource>,
)

data class ExternalPokemonEntry(
    @JsonProperty("entry_number") val entryNumber: Int,
    @JsonProperty("pokemon_species") val pokemonSpecies: NamedAPIResource,
)