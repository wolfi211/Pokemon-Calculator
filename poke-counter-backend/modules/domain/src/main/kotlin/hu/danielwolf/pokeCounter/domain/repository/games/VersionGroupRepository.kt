package hu.danielwolf.pokeCounter.domain.repository.games

import hu.danielwolf.pokeCounter.domain.model.games.VersionGroup
import org.springframework.data.jpa.repository.JpaRepository

interface VersionGroupRepository : JpaRepository<VersionGroup, Int> {
    fun findByName(name: String): VersionGroup?
}

