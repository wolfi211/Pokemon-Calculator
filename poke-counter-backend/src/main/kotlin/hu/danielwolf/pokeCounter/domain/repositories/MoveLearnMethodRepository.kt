package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.model.MoveLearnMethod
import org.springframework.data.jpa.repository.JpaRepository

interface MoveLearnMethodRepository : JpaRepository<MoveLearnMethod, Int> {
    fun findByName(name: String): MoveLearnMethod?
}

