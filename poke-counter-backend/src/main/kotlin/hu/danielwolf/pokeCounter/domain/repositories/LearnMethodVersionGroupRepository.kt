package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.model.LearnMethodVersionGroup
import org.springframework.data.jpa.repository.JpaRepository

interface LearnMethodVersionGroupRepository : JpaRepository<LearnMethodVersionGroup, Int>

