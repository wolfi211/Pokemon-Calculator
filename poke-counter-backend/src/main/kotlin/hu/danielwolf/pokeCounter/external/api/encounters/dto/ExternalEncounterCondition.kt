package hu.danielwolf.pokeCounter.external.api.encounters.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalEncounterCondition(
    val id: Int,
    val name: String,
    val names: List<ExternalName>,
    val values: List<NamedAPIResource>
)
