@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.service.games

import hu.danielwolf.pokeCounter.domain.model.games.Generation
import hu.danielwolf.pokeCounter.domain.model.games.Pokedex
import hu.danielwolf.pokeCounter.domain.model.games.PokedexVersionGroup
import hu.danielwolf.pokeCounter.domain.model.games.Version
import hu.danielwolf.pokeCounter.domain.model.games.VersionGroup
import hu.danielwolf.pokeCounter.domain.model.locations.Region
import hu.danielwolf.pokeCounter.domain.service.games.GenerationService
import hu.danielwolf.pokeCounter.domain.service.games.PokedexService
import hu.danielwolf.pokeCounter.domain.service.games.PokedexVersionGroupService
import hu.danielwolf.pokeCounter.domain.service.games.VersionGroupService
import hu.danielwolf.pokeCounter.domain.service.games.VersionService
import hu.danielwolf.pokeCounter.domain.service.locations.RegionService
import hu.danielwolf.pokeCounter.pokeApi.api.games.GameApi
import hu.danielwolf.pokeCounter.pokeApi.api.games.dto.ExternalGeneration
import hu.danielwolf.pokeCounter.pokeApi.api.games.dto.ExternalPokedex
import hu.danielwolf.pokeCounter.pokeApi.api.games.dto.ExternalVersion
import hu.danielwolf.pokeCounter.pokeApi.api.games.dto.ExternalVersionGroup
import hu.danielwolf.pokeCounter.pokeApi.api.locations.LocationApi
import hu.danielwolf.pokeCounter.pokeApi.api.locations.dto.ExternalRegion
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource
import hu.danielwolf.pokeCounter.pokeApi.config.toEntityMap
import hu.danielwolf.pokeCounter.pokeApi.config.toURI
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
        val generationSummaries = gameApi.getAllGenerations(0, 1000)
        generationSummaries.results.forEach { syncGeneration(it) }
        logger.info("Finished generation sync...")
    }

    fun syncGeneration(apiResource: NamedAPIResource) {
        logger.info("Syncing generation ${apiResource.name}")
        val externalGeneration = gameApi.followGeneration(apiResource.url.toURI())
        generationService.save(externalGeneration.toEntity())
    }

    fun syncAllRegions() {
        logger.info("Starting region sync...")
        val regionSummaries = locationApi.getAllRegions(0, 100)
        regionSummaries.results.forEach { syncRegion(it) }
        logger.info("Finished region sync...")
    }

    fun syncRegion(apiResource: NamedAPIResource) {
        logger.info("Syncing region ${apiResource.name}")
        val externalRegion = locationApi.followRegion(apiResource.url.toURI())
        val mainGeneration = externalRegion.mainGeneration?.let { generationService.getByName(it.name) }
        regionService.save(externalRegion.toEntity(mainGeneration))
    }

    fun syncAllVersionGroups() {
        logger.info("Starting version group sync...")
        val summaries = gameApi.getAllVersionGroups(0, 100)
        summaries.results.forEach {
            val external = gameApi.followVersionGroup(it.url.toURI())
            val generation = generationService.getByName(external.generation.name)
            versionGroupService.save(external.toEntity(generation))
        }
        logger.info("Finished version group sync...")
    }

    fun syncAllVersions() {
        logger.info("Starting version sync...")
        val summaries = gameApi.getAllVersions(0, 500)
        summaries.results.forEach {
            val external = gameApi.followVersion(it.url.toURI())
            val versionGroup = versionGroupService.getByName(external.versionGroup.name)
            versionService.save(external.toEntity(versionGroup))
        }
        logger.info("Finished version sync...")
    }

    fun syncAllPokedexes(): List<ExternalPokedex> {
        logger.info("Starting pokedex sync...")
        val stored = mutableListOf<ExternalPokedex>()
        val summaries = gameApi.getAllPokedexes(0, 100)
        summaries.results.forEach {
            val external = gameApi.followPokedex(it.url.toURI())
            val region = external.region?.let { regionService.getByName(it.name) }
            val pokedex = pokedexService.save(external.toEntity(region))
            if (external.versionGroups.isEmpty()) {
                val allVersionGroups = versionGroupService.findAll()
                allVersionGroups.forEach { versionGroup ->
                    pokedexVersionGroupService.save(
                        pokedexVersionGroupService.findByPokedexAndVersionGroup(pokedex, versionGroup)
                            ?: PokedexVersionGroup(pokedex = pokedex, versionGroup = versionGroup)
                    )
                }
                logger.info("Pokedex '${pokedex.name}' (id=${pokedex.id}) has no version_groups in API; linked to all ${allVersionGroups.size} version groups.")
            } else {
                external.versionGroups.forEach { vgResource ->
                    val versionGroup = versionGroupService.getByName(vgResource.name)
                    pokedexVersionGroupService.save(
                        pokedexVersionGroupService.findByPokedexAndVersionGroup(pokedex, versionGroup)
                            ?: PokedexVersionGroup(pokedex = pokedex, versionGroup = versionGroup)
                    )
                }
            }
            stored.add(external)
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

fun ExternalRegion.toEntity(mainGeneration: Generation?): Region = Region(
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

fun ExternalPokedex.toEntity(region: Region?): Pokedex = Pokedex(
    id = this.id,
    name = this.name,
    isMainSeries = this.isMainSeries,
    descriptions = this.descriptions.associate { it.language.name to it.description },
    names = this.names.toEntityMap(),
    region = region
)