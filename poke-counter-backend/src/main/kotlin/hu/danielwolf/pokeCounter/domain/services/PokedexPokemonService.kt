package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.model.PokedexPokemon
import hu.danielwolf.pokeCounter.domain.model.PokedexPokemonId
import hu.danielwolf.pokeCounter.domain.repositories.PokedexPokemonRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PokedexPokemonService(
    private val pokedexPokemonRepository: PokedexPokemonRepository,
) {

    fun getById(id: PokedexPokemonId): PokedexPokemon =
        pokedexPokemonRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "pokedexpokemon.not-found")
        }

    fun existsById(id: PokedexPokemonId): Boolean = pokedexPokemonRepository.existsById(id)

    fun save(pokedexPokemon: PokedexPokemon): PokedexPokemon =
        pokedexPokemonRepository.save(pokedexPokemon)

    fun saveAll(pokedexPokemon: Iterable<PokedexPokemon>): List<PokedexPokemon> =
        pokedexPokemonRepository.saveAll(pokedexPokemon)

    fun deleteAll() = pokedexPokemonRepository.deleteAll()
}

