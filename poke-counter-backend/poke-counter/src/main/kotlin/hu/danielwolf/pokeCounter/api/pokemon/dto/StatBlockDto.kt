package hu.danielwolf.pokeCounter.api.pokemon.dto

import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon
import kotlinx.serialization.Serializable

@Serializable
data class StatBlockDto(
  val hp: Int,
  val attack: Int,
  val defense: Int,
  val specialAttack: Int,
  val specialDefense: Int,
  val speed: Int,
)

fun Pokemon.toStatBlockDto(): StatBlockDto {
  val currentStats = stats.filter { it.generation == null }.associate { it.stat.name to it.baseStat }
  return StatBlockDto(
    hp = currentStats["hp"] ?: 0,
    attack = currentStats["attack"] ?: 0,
    defense = currentStats["defense"] ?: 0,
    specialAttack = currentStats["special-attack"] ?: 0,
    specialDefense = currentStats["special-defense"] ?: 0,
    speed = currentStats["speed"] ?: 0,
  )
}
