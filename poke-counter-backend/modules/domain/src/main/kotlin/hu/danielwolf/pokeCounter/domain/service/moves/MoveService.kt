package hu.danielwolf.pokeCounter.domain.service.moves

import hu.danielwolf.pokeCounter.domain.model.moves.Move
import hu.danielwolf.pokeCounter.domain.repository.moves.MoveRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class MoveService(
    private val moveRepository: MoveRepository,
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
}

