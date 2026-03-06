package hu.danielwolf.pokeCounter.external.api.move.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalMoveAilment(
    val id: Int,
    val name: String,
    val moves: List<NamedAPIResource>,
    val names: List<ExternalName>,
)