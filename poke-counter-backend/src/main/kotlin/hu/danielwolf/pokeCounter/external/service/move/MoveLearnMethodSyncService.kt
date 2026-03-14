package hu.danielwolf.pokeCounter.external.service.move

import hu.danielwolf.pokeCounter.domain.entities.LearnMethodVersionGroup
import hu.danielwolf.pokeCounter.domain.entities.MoveLearnMethod
import hu.danielwolf.pokeCounter.domain.entities.VersionGroup
import hu.danielwolf.pokeCounter.domain.services.LearnMethodVersionGroupService
import hu.danielwolf.pokeCounter.domain.services.MoveLearnMethodService
import hu.danielwolf.pokeCounter.domain.services.VersionGroupService
import hu.danielwolf.pokeCounter.external.api.move.MoveApi
import hu.danielwolf.pokeCounter.external.api.move.dto.ExternalLearnMethod
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import hu.danielwolf.pokeCounter.external.api.utilities.dto.PageRequest
import hu.danielwolf.pokeCounter.external.config.toEntityMap
import hu.danielwolf.pokeCounter.external.config.toURI
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
        val summaries = moveApi.getAllMoveLearnMethods(PageRequest(0, 100))
        summaries.results.forEach {
            try {
                val external = moveApi.followMoveLearnMethod(it.url.toURI())
                val method = moveLearnMethodService.save(external.toEntity())
                val versionGroups = external.versionGroups.map { vg -> versionGroupService.getByName(vg.name) }
                val junctions = versionGroups.map { vg ->
                    LearnMethodVersionGroup(learnMethod = method, versionGroup = vg)
                }
                learnMethodVersionGroupService.saveAll(junctions)
            } catch (e: Exception) {
                logger.error("Error syncing move learn method ${it.name}: ${e.message}")
            }
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
