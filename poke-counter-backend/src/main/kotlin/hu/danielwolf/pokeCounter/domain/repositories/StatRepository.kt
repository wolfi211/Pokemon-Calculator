package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entity.Stat
import org.springframework.data.jpa.repository.JpaRepository

interface StatRepository : JpaRepository<Stat, Int> {
    fun findByName(name: String): Stat?
}

