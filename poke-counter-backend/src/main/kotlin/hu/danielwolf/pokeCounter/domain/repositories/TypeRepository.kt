package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.model.Type
import org.springframework.data.jpa.repository.JpaRepository

interface TypeRepository : JpaRepository<Type, Int> {
    fun findByName(name: String): Type?
}

