package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.model.DamageClass
import org.springframework.data.jpa.repository.JpaRepository

interface DamageClassRepository : JpaRepository<DamageClass, Int> {
    fun findByName(name: String): DamageClass?
}

