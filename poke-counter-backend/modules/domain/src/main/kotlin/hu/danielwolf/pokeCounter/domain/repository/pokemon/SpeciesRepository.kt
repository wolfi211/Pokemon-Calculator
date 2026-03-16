package hu.danielwolf.pokeCounter.domain.repository.pokemon

import hu.danielwolf.pokeCounter.domain.model.pokemon.Species
import org.springframework.data.jpa.repository.JpaRepository

interface SpeciesRepository : JpaRepository<Species, Int> {
    fun findByName(name: String): Species?
}

