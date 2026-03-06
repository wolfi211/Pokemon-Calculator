package hu.danielwolf.pokeCounter.external.api.encounters.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalEncounterMethod(
    val id: Int,
    val name: String,
    val order: Int,
    val names: List<ExternalName>,
)
