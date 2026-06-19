package hu.danielwolf.pokeCounter.api.pokemon

import hu.danielwolf.pokeCounter.api.pokemon.dto.MinifiedPokemonSearchResponse
import hu.danielwolf.pokeCounter.api.pokemon.dto.toMinifiedDto
import hu.danielwolf.pokeCounter.domain.service.pokemon.PokemonService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PokemonApiService(
  private val pokemonService: PokemonService,
) {

  /**
   * Maps to DTOs inside a read-only transaction so lazy collections ([Pokemon.forms], [Pokemon.types], etc.)
   * still initialize (Open EntityManager in View is disabled).
   */
  @Transactional(readOnly = true)
  fun searchPokemonMinified(query: String?, versionGroup: Int?): List<MinifiedPokemonSearchResponse> =
    pokemonService.searchPokemonByNameAndVersionGroup(query, versionGroup).map { it.toMinifiedDto() }
}