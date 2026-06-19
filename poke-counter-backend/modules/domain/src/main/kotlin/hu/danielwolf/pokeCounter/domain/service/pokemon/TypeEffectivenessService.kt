package hu.danielwolf.pokeCounter.domain.service.pokemon

import hu.danielwolf.pokeCounter.domain.repository.pokemon.TypeRelationRepository
import org.springframework.stereotype.Service

@Service
class TypeEffectivenessService(
  private val typeRelationRepository: TypeRelationRepository,
) {
  private val chart: Map<Int, Map<Int, Double>> by lazy { buildChart() }

  fun getMultiplier(
    attackTypeId: Int,
    defenderTypeIds: Collection<Int>,
  ): Double =
    defenderTypeIds.fold(1.0) { acc, defId ->
      acc * (chart[attackTypeId]?.get(defId) ?: 1.0)
    }

  private fun buildChart(): Map<Int, Map<Int, Double>> =
    typeRelationRepository
      .findByGenerationIsNull()
      .groupBy { it.damageFromType.id }
      .mapValues { (_, relations) ->
        relations.associate { it.damageToType.id to it.multiplier.toDouble() }
      }
}
