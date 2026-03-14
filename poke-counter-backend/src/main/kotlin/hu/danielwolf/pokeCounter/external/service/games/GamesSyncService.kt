package hu.danielwolf.pokeCounter.external.service.games

import hu.danielwolf.pokeCounter.domain.entities.Generation
import hu.danielwolf.pokeCounter.domain.entities.Pokedex
import hu.danielwolf.pokeCounter.domain.entities.PokedexVersionGroup
import hu.danielwolf.pokeCounter.domain.entities.Region
import hu.danielwolf.pokeCounter.domain.entities.Version
import hu.danielwolf.pokeCounter.domain.entities.VersionGroup
import hu.danielwolf.pokeCounter.domain.services.GenerationService
import hu.danielwolf.pokeCounter.domain.services.PokedexService
import hu.danielwolf.pokeCounter.domain.services.PokedexVersionGroupService
import hu.danielwolf.pokeCounter.domain.services.RegionService
import hu.danielwolf.pokeCounter.domain.services.VersionGroupService
import hu.danielwolf.pokeCounter.domain.services.VersionService
import hu.danielwolf.pokeCounter.external.api.games.GameApi
import hu.danielwolf.pokeCounter.external.api.games.dto.ExternalGeneration
import hu.danielwolf.pokeCounter.external.api.games.dto.ExternalPokedex
import hu.danielwolf.pokeCounter.external.api.games.dto.ExternalVersion
import hu.danielwolf.pokeCounter.external.api.games.dto.ExternalVersionGroup
import hu.danielwolf.pokeCounter.external.api.locations.LocationApi
import hu.danielwolf.pokeCounter.external.api.locations.dto.ExternalRegion
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import hu.danielwolf.pokeCounter.external.api.utilities.dto.PageRequest
import hu.danielwolf.pokeCounter.external.config.toEntityMap
import hu.danielwolf.pokeCounter.external.config.toURI
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class GamesSyncService(
    private val gameApi: GameApi,
    private val locationApi: LocationApi,
    private val generationService: GenerationService,
    private val regionService: RegionService,
    private val versionGroupService: VersionGroupService,
    private val versionService: VersionService,
    private val pokedexService: PokedexService,
    private val pokedexVersionGroupService: PokedexVersionGroupService,
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
        logger.info("Syncing generation ${apiResource.name}")
        val externalGeneration = gameApi.followGeneration(apiResource.url.toURI())
        generationService.save(externalGeneration.toEntity())
    }

    fun syncAllRegions() {
        logger.info("Starting region sync...")
        val regionSummaries = locationApi.getAllRegions(PageRequest(0, 100))
        regionSummaries.results.forEach {
            try {
                syncRegion(it)
            } catch (e: Exception) {
                logger.error("Error syncing region ${it.name}: ${e.message}")
            }
        }
        logger.info("Finished region sync...")
    }

    fun syncRegion(apiResource: NamedAPIResource) {
        logger.info("Syncing region ${apiResource.name}")
        val externalRegion = locationApi.followRegion(apiResource.url.toURI())
        val mainGeneration = generationService.getByName(externalRegion.mainGeneration.name)
        regionService.save(externalRegion.toEntity(mainGeneration))
    }

    fun syncAllVersionGroups() {
        logger.info("Starting version group sync...")
        val summaries = gameApi.getAllVersionGroups(PageRequest(0, 100))
        summaries.results.forEach {
            try {
                val external = gameApi.followVersionGroup(it.url.toURI())
                val generation = generationService.getByName(external.generation.name)
                versionGroupService.save(external.toEntity(generation))
            } catch (e: Exception) {
                logger.error("Error syncing version group ${it.name}: ${e.message}")
            }
        }
        logger.info("Finished version group sync...")
    }

    fun syncAllVersions() {
        logger.info("Starting version sync...")
        val summaries = gameApi.getAllVersions(PageRequest(0, 500))
        summaries.results.forEach {
            try {
                val external = gameApi.followVersion(it.url.toURI())
                val versionGroup = versionGroupService.getByName(external.versionGroup.name)
                versionService.save(external.toEntity(versionGroup))
            } catch (e: Exception) {
                logger.error("Error syncing version ${it.name}: ${e.message}")
            }
        }
        logger.info("Finished version sync...")
    }

    /**
     * Syncs all pokedexes, fills PokedexVersionGroup, and returns the list of ExternalPokedex
     * for later PokedexPokemon fill (after Phase 4).
     */
    fun syncAllPokedexes(): List<ExternalPokedex> {
        logger.info("Starting pokedex sync...")
        val stored = mutableListOf<ExternalPokedex>()
        val summaries = gameApi.getAllPokedexes(PageRequest(0, 100))
        summaries.results.forEach {
            try {
                val external = gameApi.followPokedex(it.url.toURI())
                val region = regionService.getByName(external.region.name)
                val pokedex = pokedexService.save(external.toEntity(region))
                external.versionGroups.forEach { vgResource ->
                    val versionGroup = versionGroupService.getByName(vgResource.name)
                    pokedexVersionGroupService.save(
                        PokedexVersionGroup(pokedex = pokedex, versionGroup = versionGroup)
                    )
                }
                stored.add(external)
            } catch (e: Exception) {
                logger.error("Error syncing pokedex ${it.name}: ${e.message}")
            }
        }
        logger.info("Finished pokedex sync (stored ${stored.size} for later PokedexPokemon fill).")
        return stored
    }
}

fun ExternalGeneration.toEntity(): Generation = Generation(
    id = this.id,
    name = this.name,
    names = this.names.toEntityMap()
)

fun ExternalRegion.toEntity(mainGeneration: Generation): Region = Region(
    id = this.id,
    name = this.name,
    names = this.names.toEntityMap(),
    mainGeneration = mainGeneration
)

fun ExternalVersionGroup.toEntity(generation: Generation): VersionGroup = VersionGroup(
    id = this.id,
    name = this.name,
    order = this.order,
    generation = generation
)

fun ExternalVersion.toEntity(versionGroup: VersionGroup): Version = Version(
    id = this.id,
    name = this.name,
    names = this.names.toEntityMap(),
    versionGroup = versionGroup
)

fun ExternalPokedex.toEntity(region: Region): Pokedex = Pokedex(
    id = this.id,
    name = this.name,
    isMainSeries = this.isMainSeries,
    descriptions = this.descriptions.associate { it.language.name to it.description },
    names = this.names.toEntityMap(),
    region = region
)