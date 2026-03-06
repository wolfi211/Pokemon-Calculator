package hu.danielwolf.pokeCounter.external.api.dto.locations

import hu.danielwolf.pokeCounter.external.api.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalPalParkArea(
  val id: Int,
  val name: String,
  val names: List<ExternalName>,
  val pokemonEncounters: List<ExternalPalParkEncounterSpecies>
)

@Serializable
data class ExternalPalParkEncounterSpecies(
  val baseScore: Int,
  val rate: Int,
  val pokemonSpecies: NamedAPIResource
)