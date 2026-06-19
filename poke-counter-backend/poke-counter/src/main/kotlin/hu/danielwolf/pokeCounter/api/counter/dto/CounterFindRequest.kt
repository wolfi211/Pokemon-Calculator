package hu.danielwolf.pokeCounter.api.counter.dto

import kotlinx.serialization.Serializable

@Serializable
data class CounterFindRequest(
  val enemyPokemonId: Int,
  val enemyMoveIds: List<Int> = emptyList(),
)
