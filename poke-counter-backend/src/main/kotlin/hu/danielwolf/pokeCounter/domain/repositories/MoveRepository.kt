package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.model.Move
import org.springframework.data.jpa.repository.JpaRepository

interface MoveRepository : JpaRepository<Move, Int> {
    fun findByName(name: String): Move?
}

