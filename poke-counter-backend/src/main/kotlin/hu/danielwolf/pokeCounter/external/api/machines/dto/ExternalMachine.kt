package hu.danielwolf.pokeCounter.external.api.machines.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalMachine(
    val id: Int,
    val item: NamedAPIResource,
    val move: NamedAPIResource,
    val versionGroup: NamedAPIResource,
)