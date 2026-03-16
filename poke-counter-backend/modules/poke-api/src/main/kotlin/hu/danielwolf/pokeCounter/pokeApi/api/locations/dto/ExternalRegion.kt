@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.locations.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalRegion(
  val id: Int,
  val locations: List<NamedAPIResource>,
  val name: String,
  val names: List<ExternalName>,
  @param:JsonProperty("main_generation") val mainGeneration: NamedAPIResource?,
  val pokedexes: List<NamedAPIResource>,
  @param:JsonProperty("version_groups") val versionGroups: List<NamedAPIResource>,
)