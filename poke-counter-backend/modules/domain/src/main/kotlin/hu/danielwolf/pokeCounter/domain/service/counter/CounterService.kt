package hu.danielwolf.pokeCounter.domain.service.counter

import hu.danielwolf.pokeCounter.domain.model.moves.Move
import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokemonMove
import hu.danielwolf.pokeCounter.domain.repository.moves.MoveRepository
import hu.danielwolf.pokeCounter.domain.repository.pokemon.PokemonMoveRepository
import hu.danielwolf.pokeCounter.domain.service.pokemon.PokemonService
import hu.danielwolf.pokeCounter.domain.service.pokemon.TypeEffectivenessService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CounterService(
  private val pokemonService: PokemonService,
  private val moveRepository: MoveRepository,
  private val pokemonMoveRepository: PokemonMoveRepository,
  private val typeEffectivenessService: TypeEffectivenessService,
) {

  fun findBestCounters(input: CounterSearchInput): List<CounterResult> {
    val enemy = pokemonService.getByIdWithSummaryGraph(input.enemyPokemonId)
    val enemyTypeIds = enemy.typeIds()
    if (enemyTypeIds.isEmpty()) {
      throw ResponseStatusException(HttpStatus.BAD_REQUEST, "pokemon.missing-types")
    }

    val enemyMoveTypeIds =
      loadNonStatusEnemyMoveTypeIds(input.enemyMoveIds)
    if (enemyMoveTypeIds.isEmpty()) {
      return emptyList()
    }

    val survivors =
      pokemonService.findAllWithTypesAndCurrentStats().mapNotNull { candidate ->
        evaluateTypeMatch(candidate, enemyMoveTypeIds)
      }

    if (survivors.isEmpty()) return emptyList()

    val learnsetsByPokemonId = loadLearnsetsByPokemonId(survivors.map { it.pokemon.id })

    return survivors.mapNotNull { survivor ->
      if (!hasCounterMove(survivor.pokemon, enemyTypeIds, learnsetsByPokemonId[survivor.pokemon.id].orEmpty())) {
        return@mapNotNull null
      }
      CounterResult(
        pokemon = survivor.pokemon,
        tier = enemyMoveTypeIds.size - (survivor.immunities + survivor.resistances),
        hasStab = hasStab(survivor.pokemon, enemyTypeIds),
      )
    }.sortedBy { it.tier }
  }

  private fun loadNonStatusEnemyMoveTypeIds(enemyMoveIds: List<Int>): List<Int> {
    if (enemyMoveIds.isEmpty()) return emptyList()
    return moveRepository
      .findAllByIdWithDamageClassAndTypes(enemyMoveIds)
      .filter { it.damageClass?.name != "status" }
      .mapNotNull { it.primaryTypeId() }
  }

  private data class TypeMatchSurvivor(
    val pokemon: Pokemon,
    val immunities: Int,
    val resistances: Int,
  )

  private fun evaluateTypeMatch(
    candidate: Pokemon,
    enemyMoveTypeIds: List<Int>,
  ): TypeMatchSurvivor? {
    val defenderTypeIds = candidate.typeIds()
    if (defenderTypeIds.isEmpty()) return null

    var immunities = 0
    var resistances = 0

    for (moveTypeId in enemyMoveTypeIds) {
      val effectiveness = typeEffectivenessService.getMultiplier(moveTypeId, defenderTypeIds)
      when {
        effectiveness > 1.0 -> return null
        effectiveness == 0.0 -> immunities++
        effectiveness < 1.0 -> resistances++
      }
    }

    return TypeMatchSurvivor(candidate, immunities, resistances)
  }

  private fun loadLearnsetsByPokemonId(pokemonIds: Collection<Int>): Map<Int, List<PokemonMove>> {
    if (pokemonIds.isEmpty()) return emptyMap()
    return pokemonMoveRepository
      .findAllByPokemonIdInWithMoveGraph(pokemonIds)
      .groupBy { it.pokemon.id }
  }

  private fun hasCounterMove(
    candidate: Pokemon,
    enemyTypeIds: List<Int>,
    learnset: List<PokemonMove>,
  ): Boolean {
    val preferredDamageClass = candidate.physicalOrSpecialPreference()
    return learnset.any { pokemonMove ->
      val move = pokemonMove.move
      if (move.damageClass?.name != preferredDamageClass) return@any false
      val moveTypeId = move.primaryTypeId(setOfNotNull(pokemonMove.versionGroup?.id)) ?: return@any false
      typeEffectivenessService.getMultiplier(moveTypeId, enemyTypeIds) > 1.0
    }
  }

  private fun hasStab(
    candidate: Pokemon,
    enemyTypeIds: List<Int>,
  ): Boolean =
    candidate.typeIds().any { candidateTypeId ->
      enemyTypeIds.any { enemyTypeId ->
        typeEffectivenessService.getMultiplier(candidateTypeId, listOf(enemyTypeId)) > 1.0
      }
    }
}
