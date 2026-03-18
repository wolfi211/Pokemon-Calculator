package hu.danielwolf.pokeCounter.domain.repository.moves

import hu.danielwolf.pokeCounter.domain.model.games.VersionGroup
import hu.danielwolf.pokeCounter.domain.model.moves.LearnMethodVersionGroup
import hu.danielwolf.pokeCounter.domain.model.moves.MoveLearnMethod
import org.springframework.data.jpa.repository.JpaRepository

interface LearnMethodVersionGroupRepository : JpaRepository<LearnMethodVersionGroup, Int> {
    fun findByLearnMethodAndVersionGroup(
        learnMethod: MoveLearnMethod,
        versionGroup: VersionGroup,
    ): LearnMethodVersionGroup?
}

