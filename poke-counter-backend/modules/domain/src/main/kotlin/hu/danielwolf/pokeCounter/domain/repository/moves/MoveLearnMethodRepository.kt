package hu.danielwolf.pokeCounter.domain.repository.moves

import hu.danielwolf.pokeCounter.domain.model.moves.MoveLearnMethod
import org.springframework.data.jpa.repository.JpaRepository

interface MoveLearnMethodRepository : JpaRepository<MoveLearnMethod, Int> {
    fun findByName(name: String): MoveLearnMethod?
}

