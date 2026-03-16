@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.games.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalGeneration(
  val id: Int,
  val name: String,
  val abilities: List<NamedAPIResource>,
  val names: List<ExternalName>,
  @param:JsonProperty("main_region")
    val mainRegion: NamedAPIResource,
  val moves: List<NamedAPIResource>,
  @param:JsonProperty("pokemon_species")
    val pokemonSpecies: List<NamedAPIResource>,
  val types: List<NamedAPIResource>,
  @param:JsonProperty("version_groups")
    val versionGroups: List<NamedAPIResource>,
)