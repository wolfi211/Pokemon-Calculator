package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entity.Pokemon
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonRepository : JpaRepository<Pokemon, Int> {
    fun findByName(name: String): Pokemon?
}

