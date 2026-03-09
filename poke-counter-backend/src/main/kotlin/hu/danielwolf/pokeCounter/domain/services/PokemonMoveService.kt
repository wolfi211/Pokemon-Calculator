package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.entities.PokemonMove
import hu.danielwolf.pokeCounter.domain.repositories.PokemonMoveRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PokemonMoveService(
    private val pokemonMoveRepository: PokemonMoveRepository,
) {

    fun getById(id: Int): PokemonMove =
        pokemonMoveRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "pokemonmove.not-found")
        }

    fun save(pokemonMove: PokemonMove): PokemonMove =
        pokemonMoveRepository.save(pokemonMove)

    fun saveAll(pokemonMoves: Iterable<PokemonMove>): List<PokemonMove> =
        pokemonMoveRepository.saveAll(pokemonMoves)
}

