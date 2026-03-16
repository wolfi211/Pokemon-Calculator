@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.service.move

import hu.danielwolf.pokeCounter.domain.model.moves.DamageClass
import hu.danielwolf.pokeCounter.domain.service.moves.DamageClassService
import hu.danielwolf.pokeCounter.pokeApi.api.move.MoveApi
import hu.danielwolf.pokeCounter.pokeApi.api.move.dto.ExternalMoveDamageClass
import hu.danielwolf.pokeCounter.pokeApi.config.toEntityMap
import hu.danielwolf.pokeCounter.pokeApi.config.toURI
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class DamageClassSyncService(
    private val moveApi: MoveApi,
    private val damageClassService: DamageClassService,
    private val logger: Logger
) {

    fun syncAll() {
        logger.info("Starting damage class sync...")
        val summaries = moveApi.getAllMoveDamageClasses(0, 100)
        summaries.results.forEach {
            val external = moveApi.followMoveDamageClass(it.url.toURI())
            damageClassService.save(external.toEntity())
        }
        logger.info("Finished damage class sync.")
    }
}

fun ExternalMoveDamageClass.toEntity(): DamageClass = DamageClass(
    id = this.id,
    name = this.name,
    names = this.names.toEntityMap(),
    descriptions = this.descriptions.associate { it.language.name to it.description }
)
