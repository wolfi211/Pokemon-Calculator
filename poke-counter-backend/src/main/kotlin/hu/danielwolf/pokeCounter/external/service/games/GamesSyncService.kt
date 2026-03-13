package hu.danielwolf.pokeCounter.external.service.games

import hu.danielwolf.pokeCounter.domain.entities.Generation
import hu.danielwolf.pokeCounter.domain.repositories.GenerationRepository
import hu.danielwolf.pokeCounter.domain.services.GenerationService
import hu.danielwolf.pokeCounter.external.api.games.GameApi
import hu.danielwolf.pokeCounter.external.api.games.dto.ExternalGeneration
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import hu.danielwolf.pokeCounter.external.api.utilities.dto.PageRequest
import hu.danielwolf.pokeCounter.external.config.toEntityMap
import hu.danielwolf.pokeCounter.external.config.toURI
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class GamesSyncService(
    private val gameApi: GameApi,
    private val generationService: GenerationService,
    private val logger: Logger
) {

    fun syncAllGenerations() {
        logger.info("Starting generation sync...")

        val generationSummaries = gameApi.getAllGenerations(PageRequest(0, 1000))

        generationSummaries.results.forEach {
            try {
                syncGeneration(it)
            } catch (e: Exception) {
                logger.error("Error syncing generation ${it.name}: ${e.message}")
            }
        }
        logger.info("Finished generation sync...")
    }

    fun syncGeneration(apiResource: NamedAPIResource) {
        logger.info("Syncing ${apiResource.name}")

        val externalGeneration = gameApi.followGeneration(apiResource.url.toURI())

        generationService.save(externalGeneration.toEntity())
    }
}

fun ExternalGeneration.toEntity(): Generation = Generation(
    id = this.id,
    name = this.name,
    names = this.names.toEntityMap()
)