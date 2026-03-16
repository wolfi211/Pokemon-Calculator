package hu.danielwolf.pokeCounter.domain.repository.moves

import hu.danielwolf.pokeCounter.domain.model.moves.DamageClass
import org.springframework.data.jpa.repository.JpaRepository

interface DamageClassRepository : JpaRepository<DamageClass, Int> {
    fun findByName(name: String): DamageClass?
}

