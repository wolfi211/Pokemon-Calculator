package hu.danielwolf.pokeCounter.api.pokemon

import hu.danielwolf.pokeCounter.api.pokemon.dto.MinifiedPokemonSearchRequest
import hu.danielwolf.pokeCounter.api.pokemon.dto.MinifiedPokemonSearchResponse
import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon
import hu.danielwolf.pokeCounter.domain.service.pokemon.PokemonService
import org.springframework.stereotype.Service

@Service
class PokemonApiService(
  private val pokemonService: PokemonService,
) {

  fun searchPokemonMinified(query: MinifiedPokemonSearchRequest): List<Pokemon> {
    return pokemonService.searchPokemonByNameAndVersionGroup(query.searchText, query.versionGroup)
  }
}