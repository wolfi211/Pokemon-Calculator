package hu.danielwolf.pokeCounter.api.counter.dto

import hu.danielwolf.pokeCounter.api.pokemon.dto.PokemonSummaryDto
import kotlinx.serialization.Serializable

@Serializable
data class CounterResultDto(
  val pokemon: PokemonSummaryDto,
  val tier: Int,
  val hasStab: Boolean,
)

@Serializable
data class CounterFindResponse(
  val results: List<CounterResultDto>,
)
