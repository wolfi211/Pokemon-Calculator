package hu.danielwolf.pokeCounter.domain.service.pokemon

import hu.danielwolf.pokeCounter.domain.model.pokemon.PokedexPokemon
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokedexPokemonId
import hu.danielwolf.pokeCounter.domain.repository.pokemon.PokedexPokemonRepository
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

