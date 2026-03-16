@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.service.move

import hu.danielwolf.pokeCounter.domain.model.games.Generation
import hu.danielwolf.pokeCounter.domain.model.moves.DamageClass
import hu.danielwolf.pokeCounter.domain.model.moves.Move
import hu.danielwolf.pokeCounter.domain.model.moves.MoveType
import hu.danielwolf.pokeCounter.domain.service.games.GenerationService
import hu.danielwolf.pokeCounter.domain.service.games.VersionGroupService
import hu.danielwolf.pokeCounter.domain.service.moves.DamageClassService
import hu.danielwolf.pokeCounter.domain.service.moves.MoveService
import hu.danielwolf.pokeCounter.domain.service.moves.MoveTypeService
import hu.danielwolf.pokeCounter.domain.service.pokemon.TypeService
import hu.danielwolf.pokeCounter.pokeApi.api.move.MoveApi
import hu.danielwolf.pokeCounter.pokeApi.api.move.dto.ExternalMove
import hu.danielwolf.pokeCounter.pokeApi.config.toEntityMap
import hu.danielwolf.pokeCounter.pokeApi.config.toURI
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class MovesSyncService(
  private val moveApi: MoveApi,
  private val moveService: MoveService,
  private val moveTypeService: MoveTypeService,
  private val damageClassService: DamageClassService,
  private val generationService: GenerationService,
  private val typeService: TypeService,
  private val versionGroupService: VersionGroupService,
  private val logger: Logger
) {

  fun syncAll() {
    logger.info("Starting move sync...")
    val summaries = moveApi.getAllMoves(0, 2000)
    summaries.results.forEach {
      val external = moveApi.followMove(it.url.toURI())
      val damageClass = damageClassService.getByName(external.damageClass.name)
      val generation = generationService.getByName(external.generation.name)
      val move = moveService.save(external.toEntity(damageClass, generation))
      val moveTypes = buildMoveTypes(move, external)
      if (moveTypes.isNotEmpty()) {
        moveTypeService.saveAll(moveTypes)
      }
    }
    logger.info("Finished move sync.")
  }

  private fun buildMoveTypes(move: Move, external: ExternalMove): List<MoveType> {
    val result = mutableListOf<MoveType>()
    val currentType = typeService.getByName(external.type.name)
    result.add(
      MoveType(
        move = move,
        accuracy = external.accuracy,
        effectChance = external.effectChance,
        pp = external.pp ?: -1,
        power = external.power,
        effectEntries = external.effectEntries.associate { it.language.name to it.shortEffect },
        type = currentType,
        versionGroup = null
      )
    )
    external.pastValues?.forEach { past ->
      val versionGroup = versionGroupService.getByName(past.versionGroup.name)
      val pastType = typeService.getByName(past.type?.name ?: external.type.name)
      val effectEntries =
        past.effectEntries?.let { effectEntry -> effectEntry.associate { it.language.name to it.shortEffect } }
          .takeIf { !it.isNullOrEmpty() }
          ?: external.effectEntries.associate { it.language.name to it.shortEffect }
      result.add(
        MoveType(
          move = move,
          accuracy = past.accuracy ?: external.accuracy,
          effectChance = past.effectChance ?: external.effectChance,
          pp = past.pp ?: external.pp,
          power = past.power ?: external.power,
          effectEntries = effectEntries,
          type = pastType,
          versionGroup = versionGroup
        )
      )
    }
    return result
  }
}

fun ExternalMove.toEntity(
  damageClass: DamageClass,
  generation: Generation
): Move = Move(
  id = this.id,
  name = this.name,
  priority = this.priority,
  damageClass = damageClass,
  flavorTexts = this.flavorTextEntries.groupBy { it.language.name }.mapValues { it.value.first().flavorText },
  generation = generation,
  names = this.names.toEntityMap()
)
