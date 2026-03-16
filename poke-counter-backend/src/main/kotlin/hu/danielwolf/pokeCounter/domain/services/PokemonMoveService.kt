package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.model.Move
import hu.danielwolf.pokeCounter.domain.model.Pokemon
import hu.danielwolf.pokeCounter.domain.model.PokemonMove
import hu.danielwolf.pokeCounter.domain.model.VersionGroup
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

    fun getByPokemonAndMoveAndVersionGroup(pokemon: Pokemon, move: Move, versionGroup: VersionGroup): PokemonMove? =
        pokemonMoveRepository.findByPokemonAndMoveAndVersionGroup(pokemon, move, versionGroup)
}

