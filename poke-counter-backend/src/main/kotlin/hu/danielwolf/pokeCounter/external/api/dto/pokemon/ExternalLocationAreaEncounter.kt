package hu.danielwolf.pokeCounter.external.api.dto.pokemon

import hu.danielwolf.pokeCounter.external.api.dto.ExternalVersionEncounterDetail
import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalLocationAreaEncounter(
  val locationArea: NamedAPIResource,
  val versionDetails: List<ExternalVersionEncounterDetail>
)