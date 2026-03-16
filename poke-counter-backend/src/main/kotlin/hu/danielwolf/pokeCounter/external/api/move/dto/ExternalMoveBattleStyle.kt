package hu.danielwolf.pokeCounter.external.api.move.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName

data class ExternalMoveBattleStyle(
    val id: Int,
    val name: String,
    val names: List<ExternalName>,
)