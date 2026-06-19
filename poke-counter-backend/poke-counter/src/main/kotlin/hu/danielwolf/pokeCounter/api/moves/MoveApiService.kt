package hu.danielwolf.pokeCounter.api.moves

import hu.danielwolf.pokeCounter.api.moves.dto.MinifiedMoveSearchResponse
import hu.danielwolf.pokeCounter.api.moves.dto.toMinifiedSearchDto
import hu.danielwolf.pokeCounter.domain.service.moves.MoveService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MoveApiService(
  private val moveService: MoveService,
) {
  @Transactional(readOnly = true)
  fun searchMovesMinified(query: String?, pokemonId: Int?): List<MinifiedMoveSearchResponse> {
    val moves = moveService.searchMovesByNameAndPokemon(query, pokemonId)
    val vgByMoveId: Map<Int, Set<Int>> =
      if (pokemonId != null && moves.isNotEmpty()) {
        moveService.learnableVersionGroupIdsByMove(pokemonId, moves.map { it.id })
      } else {
        emptyMap()
      }
    return moves.map { move ->
      move.toMinifiedSearchDto(vgByMoveId[move.id])
    }
  }
}
