@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.games.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalDescription
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalPokedex(
  val id: Int,
  val name: String,
  @param:JsonProperty("is_main_series") val isMainSeries: Boolean,
  val descriptions: List<ExternalDescription>,
  val names: List<ExternalName>,
  @param:JsonProperty("pokemon_entries") val pokemonEntries: List<ExternalPokemonEntry>,
  val region: NamedAPIResource?,
  @param:JsonProperty("version_groups") val versionGroups: List<NamedAPIResource>,
)

data class ExternalPokemonEntry(
  @param:JsonProperty("entry_number") val entryNumber: Int,
  @param:JsonProperty("pokemon_species") val pokemonSpecies: NamedAPIResource,
)