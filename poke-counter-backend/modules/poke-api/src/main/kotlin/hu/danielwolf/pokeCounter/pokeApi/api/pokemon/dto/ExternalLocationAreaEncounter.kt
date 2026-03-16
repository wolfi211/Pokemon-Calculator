@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalVersionEncounterDetail
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalLocationAreaEncounter(
  @param:JsonProperty("location_area") val locationArea: NamedAPIResource,
  @param:JsonProperty("version_details") val versionDetails: List<ExternalVersionEncounterDetail>
)