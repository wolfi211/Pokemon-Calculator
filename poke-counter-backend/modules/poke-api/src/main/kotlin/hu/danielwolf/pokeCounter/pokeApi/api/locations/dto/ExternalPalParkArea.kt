@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.locations.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalPalParkArea(
  val id: Int,
  val name: String,
  val names: List<ExternalName>,
  @param:JsonProperty("pokemon_encounters") val pokemonEncounters: List<ExternalPalParkEncounterSpecies>
)

data class ExternalPalParkEncounterSpecies(
  @param:JsonProperty("base_score") val baseScore: Int,
  val rate: Int,
  @param:JsonProperty("pokemon_species") val pokemonSpecies: NamedAPIResource
)