package hu.danielwolf.pokeCounter.domain.repository.moves

import hu.danielwolf.pokeCounter.domain.model.moves.LearnMethodVersionGroup
import org.springframework.data.jpa.repository.JpaRepository

interface LearnMethodVersionGroupRepository : JpaRepository<LearnMethodVersionGroup, Int>

