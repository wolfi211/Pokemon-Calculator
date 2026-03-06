package hu.danielwolf.pokeCounter.external.api.dto.locations

import hu.danielwolf.pokeCounter.external.api.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.dto.ExternalVersionEncounterDetail
import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalLocationArea(
  val id: Int,
  val name: String,
  val gameIndex: Int,
  val encounterMethodRates: List<ExternalMethodRate>,
  val location: NamedAPIResource,
  val names: List<ExternalName>,
  val pokemonEncounters: List<ExternalPokemonEncounter>,
)

@Serializable
data class ExternalMethodRate(
  val encounterMethod: NamedAPIResource,
  val versionDetails: List<ExternalEncounterVersionDetail>,
)

@Serializable
data class ExternalEncounterVersionDetail(
  val rate: Int,
  val version: NamedAPIResource,
)

@Serializable
data class ExternalPokemonEncounter(
  val pokemon: NamedAPIResource,
  val versionDetails: List<ExternalVersionEncounterDetail>,
)
