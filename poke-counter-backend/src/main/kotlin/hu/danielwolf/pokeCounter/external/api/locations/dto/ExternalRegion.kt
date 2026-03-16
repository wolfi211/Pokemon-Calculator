package hu.danielwolf.pokeCounter.external.api.locations.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalRegion(
    val id: Int,
    val locations: List<NamedAPIResource>,
    val name: String,
    val names: List<ExternalName>,
    @JsonProperty("main_generation") val mainGeneration: NamedAPIResource?,
    val pokedexes: List<NamedAPIResource>,
    @JsonProperty("version_groups") val versionGroups: List<NamedAPIResource>,
)