package hu.danielwolf.pokeCounter.external.api.move.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalDescription
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource

data class ExternalMoveCategory(
    val id: Int,
    val name: String,
    val moves: List<NamedAPIResource>,
    val descriptions: List<ExternalDescription>,
)