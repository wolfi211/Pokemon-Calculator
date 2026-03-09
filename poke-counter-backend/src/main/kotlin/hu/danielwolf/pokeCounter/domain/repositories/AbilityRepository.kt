package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entity.Ability
import org.springframework.data.jpa.repository.JpaRepository

interface AbilityRepository : JpaRepository<Ability, Int> {
    fun findByName(name: String): Ability?
}

