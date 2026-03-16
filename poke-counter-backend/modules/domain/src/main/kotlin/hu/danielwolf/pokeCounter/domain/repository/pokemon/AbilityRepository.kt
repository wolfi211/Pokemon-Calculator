package hu.danielwolf.pokeCounter.domain.repository.pokemon

import hu.danielwolf.pokeCounter.domain.model.pokemon.Ability
import org.springframework.data.jpa.repository.JpaRepository

interface AbilityRepository : JpaRepository<Ability, Int> {
    fun findByName(name: String): Ability?
}

