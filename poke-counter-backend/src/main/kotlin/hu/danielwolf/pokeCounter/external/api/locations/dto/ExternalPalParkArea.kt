package hu.danielwolf.pokeCounter.external.api.locations.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalPalParkArea(
    val id: Int,
    val name: String,
    val names: List<ExternalName>,
    @JsonProperty("pokemon_encounters") val pokemonEncounters: List<ExternalPalParkEncounterSpecies>
)

data class ExternalPalParkEncounterSpecies(
  @JsonProperty("base_score") val baseScore: Int,
  val rate: Int,
  @JsonProperty("pokemon_species") val pokemonSpecies: NamedAPIResource
)