@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.service.pokemon

import hu.danielwolf.pokeCounter.domain.model.pokemon.Stat
import hu.danielwolf.pokeCounter.domain.service.pokemon.StatService
import hu.danielwolf.pokeCounter.pokeApi.api.pokemon.PokemonApi
import hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto.ExternalStat
import hu.danielwolf.pokeCounter.pokeApi.config.toURI
import org.slf4j.Logger
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