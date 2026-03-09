package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entities.Pokedex
import org.springframework.data.jpa.repository.JpaRepository

interface PokedexRepository : JpaRepository<Pokedex, Int> {
    fun findByName(name: String): Pokedex?
}

