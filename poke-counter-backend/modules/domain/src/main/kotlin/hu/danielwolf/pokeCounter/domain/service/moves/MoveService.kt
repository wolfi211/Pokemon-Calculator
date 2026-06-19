package hu.danielwolf.pokeCounter.domain.service.moves

import hu.danielwolf.pokeCounter.domain.model.moves.Move
import hu.danielwolf.pokeCounter.domain.repository.moves.MoveRepository
import hu.danielwolf.pokeCounter.domain.repository.pokemon.PokemonMoveRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class MoveService(
    private val moveRepository: MoveRepository,
    private val pokemonMoveRepository: PokemonMoveRepository,
) {

    fun getById(id: Int): Move =
        moveRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "move.not-found")
        }

    fun getByName(name: String): Move =
        moveRepository.findByName(name) ?: throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "move.not-found",
        )

    fun save(move: Move): Move =
        moveRepository.save(move)

    fun saveAll(moves: Iterable<Move>): List<Move> =
        moveRepository.saveAll(moves)

    fun searchMovesByNameAndPokemon(searchText: String?, pokemonId: Int?): List<Move> =
        moveRepository.findIdsForEnglishMoveSearch(searchText, pokemonId).let { ids ->
            if (ids.isEmpty()) {
                emptyList()
            } else {
                moveRepository.findAllByIdWithMinifiedSearchGraph(ids).sortedBy { it.id }
            }
        }

    /**
     * For each move id, version groups in which [pokemonId] can learn that move (from [pokemon_moves]).
     */
    fun learnableVersionGroupIdsByMove(
        pokemonId: Int,
        moveIds: Collection<Int>,
    ): Map<Int, Set<Int>> {
        if (moveIds.isEmpty()) return emptyMap()
        return pokemonMoveRepository
            .findMoveIdAndVersionGroupIdByPokemonAndMoveIdIn(pokemonId, moveIds)
            .groupBy(
                keySelector = { row -> (row[0] as Number).toInt() },
                valueTransform = { row -> (row[1] as Number).toInt() },
            ).mapValues { (_, vgs) -> vgs.toSet() }
    }
}

