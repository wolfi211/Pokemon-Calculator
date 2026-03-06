package hu.danielwolf.pokeCounter.external.api.dto.regions

import hu.danielwolf.pokeCounter.external.api.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class Region(
  val id: Int,
  val locations: List<NamedAPIResource>,
  val name: String,
  val names: List<ExternalName>,
  val mainGeneration: NamedAPIResource,
  val pokedexes: List<NamedAPIResource>,
  val versionGroups: List<NamedAPIResource>,
)
