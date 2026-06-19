package hu.danielwolf.pokeCounter.domain.service.counter

import hu.danielwolf.pokeCounter.domain.model.moves.Move
import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon

fun Move.primaryTypeId(learnableVersionGroupIds: Set<Int>? = null): Int? {
  val rows = types.filter { it.type != null }
  if (rows.isEmpty()) return null
  val allowed = learnableVersionGroupIds?.takeIf { it.isNotEmpty() }
  val candidates =
    if (allowed == null) {
      rows
    } else {
      rows.filter { row ->
        val vgId = row.versionGroup?.id
        vgId != null && allowed.contains(vgId)
      }
    }
  val chosen = candidates.ifEmpty { rows }
  return chosen.maxByOrNull { it.versionGroup?.id ?: Int.MIN_VALUE }?.type?.id
}

fun Pokemon.typeIds(): List<Int> = types.sortedBy { it.slot ?: 0 }.map { it.type.id }

fun Pokemon.baseStat(statName: String): Int =
  stats.firstOrNull { it.generation == null && it.stat.name == statName }?.baseStat ?: 0

fun Pokemon.physicalOrSpecialPreference(): String =
  if (baseStat("attack") >= baseStat("special-attack")) "physical" else "special"
