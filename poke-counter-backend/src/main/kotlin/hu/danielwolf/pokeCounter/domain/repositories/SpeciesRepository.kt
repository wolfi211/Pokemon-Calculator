package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entities.Species
import org.springframework.data.jpa.repository.JpaRepository

interface SpeciesRepository : JpaRepository<Species, Int> {
    fun findByName(name: String): Species?
}

