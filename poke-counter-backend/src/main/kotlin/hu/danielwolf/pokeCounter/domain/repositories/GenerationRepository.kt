package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entities.Generation
import org.springframework.data.jpa.repository.JpaRepository

interface GenerationRepository : JpaRepository<Generation, Int> {
    fun findByName(name: String): Generation?
}

