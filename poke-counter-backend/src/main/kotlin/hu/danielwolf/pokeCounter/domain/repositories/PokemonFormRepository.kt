package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entities.PokemonForm
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonFormRepository : JpaRepository<PokemonForm, Int> {
    fun findByName(name: String): PokemonForm?
}

