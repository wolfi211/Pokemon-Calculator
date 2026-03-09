package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.entities.Pokemon
import hu.danielwolf.pokeCounter.domain.repositories.PokemonRepository
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
}

