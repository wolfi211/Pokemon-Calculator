package hu.danielwolf.pokeCounter.external.api.pokemon.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalVersionEncounterDetail
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalLocationAreaEncounter(
    val locationArea: NamedAPIResource,
    val versionDetails: List<ExternalVersionEncounterDetail>
)