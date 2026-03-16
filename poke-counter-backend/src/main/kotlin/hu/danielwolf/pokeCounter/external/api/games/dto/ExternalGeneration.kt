package hu.danielwolf.pokeCounter.external.api.games.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalGeneration(
    val id: Int,
    val name: String,
    val abilities: List<NamedAPIResource>,
    val names: List<ExternalName>,
    @JsonProperty("main_region")
    val mainRegion: NamedAPIResource,
    val moves: List<NamedAPIResource>,
    @JsonProperty("pokemon_species")
    val pokemonSpecies: List<NamedAPIResource>,
    val types: List<NamedAPIResource>,
    @JsonProperty("version_groups")
    val versionGroups: List<NamedAPIResource>,
)