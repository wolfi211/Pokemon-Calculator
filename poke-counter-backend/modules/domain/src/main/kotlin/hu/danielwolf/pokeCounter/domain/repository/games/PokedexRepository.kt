package hu.danielwolf.pokeCounter.domain.repository.games

import hu.danielwolf.pokeCounter.domain.model.games.Pokedex
import org.springframework.data.jpa.repository.JpaRepository

interface PokedexRepository : JpaRepository<Pokedex, Int> {
    fun findByName(name: String): Pokedex?
}

