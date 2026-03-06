package hu.danielwolf.pokeCounter.external.api.move.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalMoveBattleStyle(
    val id: Int,
    val name: String,
    val names: List<ExternalName>,
)