@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.service.move

import hu.danielwolf.pokeCounter.domain.model.moves.LearnMethodVersionGroup
import hu.danielwolf.pokeCounter.domain.model.moves.MoveLearnMethod
import hu.danielwolf.pokeCounter.domain.service.games.VersionGroupService
import hu.danielwolf.pokeCounter.domain.service.moves.LearnMethodVersionGroupService
import hu.danielwolf.pokeCounter.domain.service.moves.MoveLearnMethodService
import hu.danielwolf.pokeCounter.pokeApi.api.move.MoveApi
import hu.danielwolf.pokeCounter.pokeApi.api.move.dto.ExternalLearnMethod
import hu.danielwolf.pokeCounter.pokeApi.config.toEntityMap
import hu.danielwolf.pokeCounter.pokeApi.config.toURI
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class MoveLearnMethodSyncService(
    private val moveApi: MoveApi,
    private val moveLearnMethodService: MoveLearnMethodService,
    private val versionGroupService: VersionGroupService,
    private val learnMethodVersionGroupService: LearnMethodVersionGroupService,
    private val logger: Logger
) {

    fun syncAll() {
        logger.info("Starting move learn method sync...")
        val summaries = moveApi.getAllMoveLearnMethods(0, 100)
        summaries.results.forEach {
            val external = moveApi.followMoveLearnMethod(it.url.toURI())
            val method = moveLearnMethodService.save(external.toEntity())
            val versionGroups = external.versionGroups.map { vg -> versionGroupService.getByName(vg.name) }
            val junctions = versionGroups.map { vg ->
                learnMethodVersionGroupService.findByLearnMethodAndVersionGroup(method, vg)
                    ?: LearnMethodVersionGroup(learnMethod = method, versionGroup = vg)
            }
            learnMethodVersionGroupService.saveAll(junctions)
        }
        logger.info("Finished move learn method sync.")
    }
}

fun ExternalLearnMethod.toEntity(): MoveLearnMethod = MoveLearnMethod(
    id = this.id,
    name = this.name,
    descriptions = this.descriptions.associate { it.language.name to it.description },
    names = this.names.toEntityMap()
)
