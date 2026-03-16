package hu.danielwolf.pokeCounter.external.api.pokemon.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalVersionEncounterDetail
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalLocationAreaEncounter(
    @JsonProperty("location_area") val locationArea: NamedAPIResource,
    @JsonProperty("version_details") val versionDetails: List<ExternalVersionEncounterDetail>
)