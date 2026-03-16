package hu.danielwolf.pokeCounter.domain.repository.moves

import hu.danielwolf.pokeCounter.domain.model.moves.Move
import org.springframework.data.jpa.repository.JpaRepository

interface MoveRepository : JpaRepository<Move, Int> {
    fun findByName(name: String): Move?
}

