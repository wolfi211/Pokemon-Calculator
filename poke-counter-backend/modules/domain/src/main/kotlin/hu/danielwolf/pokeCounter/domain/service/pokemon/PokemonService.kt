package hu.danielwolf.pokeCounter.domain.service.pokemon

import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon
import hu.danielwolf.pokeCounter.domain.repository.pokemon.PokemonRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PokemonService(
  private val pokemonRepository: PokemonRepository,
) {

  fun getById(id: Int): Pokemon =
    pokemonRepository.findById(id).orElseThrow {
      ResponseStatusException(HttpStatus.BAD_REQUEST, "pokemon.not-found")
    }

  fun getByName(name: String): Pokemon =
    pokemonRepository.findByName(name) ?: throw ResponseStatusException(
      HttpStatus.BAD_REQUEST,
      "pokemon.not-found",
    )

  fun getAll(): List<Pokemon> =
    pokemonRepository.findAll()

  fun save(pokemon: Pokemon): Pokemon =
    pokemonRepository.save(pokemon)

  fun saveAll(pokemon: Iterable<Pokemon>): List<Pokemon> =
    pokemonRepository.saveAll(pokemon)

  fun searchPokemonByNameAndVersionGroup(searchText: String?, versionGroup: Int?): List<Pokemon> =
    pokemonRepository.findIdsForLocalizedSearch(searchText, versionGroup).let { ids ->
      if (ids.isEmpty()) {
        emptyList()
      } else {
        loadPokemonGraphByIds(ids)
        ids.mapNotNull { pokemonRepository.findById(it).orElse(null) }.sortedBy { it.id }
      }
    }

  fun getByIdWithSummaryGraph(id: Int): Pokemon {
    loadPokemonGraphByIds(listOf(id))
    return getById(id)
  }

  fun findAllWithTypesAndCurrentStats(): List<Pokemon> =
    pokemonRepository.findAllWithTypesAndCurrentStats().sortedBy { it.id }

  fun loadSummaryGraphByIds(ids: Collection<Int>): List<Pokemon> {
    if (ids.isEmpty()) return emptyList()
    loadPokemonGraphByIds(ids)
    return ids.mapNotNull { pokemonRepository.findById(it).orElse(null) }.sortedBy { it.id }
  }

  private fun loadPokemonGraphByIds(ids: Collection<Int>) {
    pokemonRepository.findAllByIdWithSpeciesAndForms(ids)
    pokemonRepository.findAllByIdWithTypesAndTypeEntity(ids)
    pokemonRepository.findAllByIdWithCurrentStats(ids)
  }
}

