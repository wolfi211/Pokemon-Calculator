package hu.danielwolf.pokeCounter.external.api.encounters.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalEncounterConditionValue(
    val id: Int,
    val name: String,
    val condition: NamedAPIResource,
    val names: List<ExternalName>,
)
