package hu.danielwolf.pokeCounter.domain.repository.pokemon

import hu.danielwolf.pokeCounter.domain.model.pokemon.Stat
import org.springframework.data.jpa.repository.JpaRepository

interface StatRepository : JpaRepository<Stat, Int> {
    fun findByName(name: String): Stat?
}

