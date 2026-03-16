package hu.danielwolf.pokeCounter.external.service.pokemon

import hu.danielwolf.pokeCounter.domain.entities.Stat
import org.slf4j.Logger
import hu.danielwolf.pokeCounter.domain.services.StatService
import hu.danielwolf.pokeCounter.external.api.pokemon.PokemonApi
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalStat
import hu.danielwolf.pokeCounter.external.config.toURI
import org.springframework.stereotype.Service

@Service
class StatSyncService(
  private val pokemonApi: PokemonApi,
  private val statService: StatService,
  private val logger: Logger
) {

  fun syncAll() {
    logger.info("Starting stat sync...")

    val externalStats = pokemonApi.getAllStats(limit = 10000)

    externalStats.results.forEach {
      val externalStat = pokemonApi.followStat(it.url.toURI())
      statService.save(externalStat.toEntity())
    }
  }
}

fun ExternalStat.toEntity(): Stat = Stat(
  id = this.id,
  name = this.name,
  names = this.names.associate { it.language.name to it.name }
)