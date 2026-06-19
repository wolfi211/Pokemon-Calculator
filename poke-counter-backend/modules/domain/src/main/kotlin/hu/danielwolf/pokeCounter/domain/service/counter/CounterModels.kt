package hu.danielwolf.pokeCounter.domain.service.counter

import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon

data class CounterSearchInput(
  val enemyPokemonId: Int,
  val enemyMoveIds: List<Int>,
)

data class CounterResult(
  val pokemon: Pokemon,
  val tier: Int,
  val hasStab: Boolean,
)
