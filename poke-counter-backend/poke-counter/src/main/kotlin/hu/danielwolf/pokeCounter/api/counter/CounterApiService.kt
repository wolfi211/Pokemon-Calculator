package hu.danielwolf.pokeCounter.api.counter

import hu.danielwolf.pokeCounter.api.counter.dto.CounterFindRequest
import hu.danielwolf.pokeCounter.api.counter.dto.CounterFindResponse
import hu.danielwolf.pokeCounter.api.counter.dto.CounterResultDto
import hu.danielwolf.pokeCounter.api.pokemon.dto.toSummaryDto
import hu.danielwolf.pokeCounter.domain.service.counter.CounterSearchInput
import hu.danielwolf.pokeCounter.domain.service.counter.CounterService
import hu.danielwolf.pokeCounter.domain.service.pokemon.PokemonService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CounterApiService(
  private val counterService: CounterService,
  private val pokemonService: PokemonService,
) {
  @Transactional(readOnly = true)
  fun findCounters(request: CounterFindRequest): CounterFindResponse {
    val domainResults =
      counterService.findBestCounters(
        CounterSearchInput(
          enemyPokemonId = request.enemyPokemonId,
          enemyMoveIds = request.enemyMoveIds,
        ),
      )

    val pokemonById =
      pokemonService
        .loadSummaryGraphByIds(domainResults.map { it.pokemon.id })
        .associateBy { it.id }

    val results =
      domainResults.mapNotNull { result ->
        val pokemon = pokemonById[result.pokemon.id] ?: return@mapNotNull null
        CounterResultDto(
          pokemon = pokemon.toSummaryDto(),
          tier = result.tier,
          hasStab = result.hasStab,
        )
      }

    return CounterFindResponse(results = results)
  }
}
