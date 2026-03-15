package hu.danielwolf.pokeCounter.external.service.move

import hu.danielwolf.pokeCounter.domain.entities.DamageClass
import hu.danielwolf.pokeCounter.domain.entities.Generation
import hu.danielwolf.pokeCounter.domain.entities.Move
import hu.danielwolf.pokeCounter.domain.entities.MoveType
import hu.danielwolf.pokeCounter.domain.services.DamageClassService
import hu.danielwolf.pokeCounter.domain.services.GenerationService
import hu.danielwolf.pokeCounter.domain.services.MoveService
import hu.danielwolf.pokeCounter.domain.services.MoveTypeService
import hu.danielwolf.pokeCounter.domain.services.TypeService
import hu.danielwolf.pokeCounter.domain.services.VersionGroupService
import hu.danielwolf.pokeCounter.external.api.move.MoveApi
import hu.danielwolf.pokeCounter.external.api.move.dto.ExternalMove
import hu.danielwolf.pokeCounter.external.api.move.dto.ExternalPastMoveStatValue
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import hu.danielwolf.pokeCounter.external.api.utilities.dto.PageRequest
import hu.danielwolf.pokeCounter.external.config.toEntityMap
import hu.danielwolf.pokeCounter.external.config.toURI
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
        val summaries = moveApi.getAllMoves(PageRequest(0, 2000))
        summaries.results.forEach {
            try {
                val external = moveApi.followMove(it.url.toURI())
                val damageClass = damageClassService.getByName(external.damageClass.name)
                val generation = generationService.getByName(external.generation.name)
                val move = moveService.save(external.toEntity(damageClass, generation))
                val moveTypes = buildMoveTypes(move, external)
                if (moveTypes.isNotEmpty()) {
                    moveTypeService.saveAll(moveTypes)
                }
            } catch (e: Exception) {
                logger.error("Error syncing move ${it.name}: ${e.message}")
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
                accuracy = external.accuracy.takeIf { it > 0 },
                effectChance = external.effectChance.takeIf { it > 0 },
                pp = external.pp.takeIf { it > 0 },
                power = external.power.takeIf { it > 0 },
                effectEntries = external.effectEntries.associate { it.language.name to it.shortEffect },
                type = currentType,
                versionGroup = null
            )
        )
        external.pastValues.forEach { past ->
            val versionGroup = versionGroupService.getByName(past.versionGroup.name)
            val pastType = typeService.getByName(past.type.name)
            result.add(
                MoveType(
                    move = move,
                    accuracy = past.accuracy.takeIf { it > 0 },
                    effectChance = past.effectChance.takeIf { it > 0 },
                    pp = past.pp.takeIf { it > 0 },
                    power = past.power.takeIf { it > 0 },
                    effectEntries = past.effectEntries.associate { it.language.name to it.shortEffect },
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
