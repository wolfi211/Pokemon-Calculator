package hu.danielwolf.pokeCounter.domain.repository.games

import hu.danielwolf.pokeCounter.domain.model.games.Generation
import org.springframework.data.jpa.repository.JpaRepository

interface GenerationRepository : JpaRepository<Generation, Int> {
    fun findByName(name: String): Generation?
}

