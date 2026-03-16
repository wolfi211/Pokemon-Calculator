package hu.danielwolf.pokeCounter.domain.service.pokemon

import hu.danielwolf.pokeCounter.domain.model.games.VersionGroup
import hu.danielwolf.pokeCounter.domain.model.moves.Move
import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokemonMove
import hu.danielwolf.pokeCounter.domain.repository.pokemon.PokemonMoveRepository
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

