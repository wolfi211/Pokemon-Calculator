package hu.danielwolf.pokeCounter.api.moves.dto

import hu.danielwolf.pokeCounter.api.pokemon.dto.MinifiedTypeDto
import hu.danielwolf.pokeCounter.api.pokemon.dto.toMinifiedType
import hu.danielwolf.pokeCounter.domain.model.moves.DamageClass
import hu.danielwolf.pokeCounter.domain.model.moves.Move
import hu.danielwolf.pokeCounter.domain.model.moves.MoveType
import kotlinx.serialization.Serializable

@Serializable
data class MinifiedDamageClassDto(
  val id: Int,
  val name: String,
  val localizedName: String,
)

@Serializable
data class MinifiedMoveSearchResponse(
  val id: Int,
  val name: String,
  val types: Set<MinifiedTypeDto>,
  val damageClass: MinifiedDamageClassDto?,
  val localizedName: String,
)

/**
 * @param learnableVersionGroupIds version groups in which the context Pokémon can learn this move;
 *   used to pick a [MoveType] row. If null or empty, falls back to the row with the highest version group id.
 */
fun Move.toMinifiedSearchDto(learnableVersionGroupIds: Set<Int>?): MinifiedMoveSearchResponse {
  val moveType = selectMoveTypeForLearnableGroups(learnableVersionGroupIds)
  val types =
    moveType
      ?.takeIf { it.type != null }
      ?.let { setOf(it.toMinifiedType()) }
      ?: emptySet()
  return MinifiedMoveSearchResponse(
    id = id,
    name = name,
    types = types,
    damageClass = damageClass?.toMinifiedDamageClassDto(),
    localizedName = names?.get("en")?.takeIf { it.isNotBlank() } ?: name,
  )
}

private fun Move.selectMoveTypeForLearnableGroups(learnableVersionGroupIds: Set<Int>?): MoveType? {
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
  return chosen.maxByOrNull { it.versionGroup?.id ?: Int.MIN_VALUE }
}

private fun DamageClass.toMinifiedDamageClassDto(): MinifiedDamageClassDto =
  MinifiedDamageClassDto(
    id = id,
    name = name,
    localizedName = names?.get("en")?.takeIf { it.isNotBlank() } ?: name,
  )
