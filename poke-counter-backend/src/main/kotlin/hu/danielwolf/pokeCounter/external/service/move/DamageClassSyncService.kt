package hu.danielwolf.pokeCounter.external.service.move

import hu.danielwolf.pokeCounter.domain.entities.DamageClass
import hu.danielwolf.pokeCounter.domain.services.DamageClassService
import hu.danielwolf.pokeCounter.external.api.move.MoveApi
import hu.danielwolf.pokeCounter.external.api.move.dto.ExternalMoveDamageClass
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import hu.danielwolf.pokeCounter.external.config.toEntityMap
import hu.danielwolf.pokeCounter.external.config.toURI
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
