package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entity.LearnMethodVersionGroup
import org.springframework.data.jpa.repository.JpaRepository

interface LearnMethodVersionGroupRepository : JpaRepository<LearnMethodVersionGroup, Int>

