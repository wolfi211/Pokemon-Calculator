package hu.danielwolf.pokeCounter.external.api.move.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalDescription
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalMoveTarget(
    val id: Int,
    val name: String,
    val descriptions: List<ExternalDescription>,
    val moves: List<NamedAPIResource>,
    val names: List<ExternalName>,
)
