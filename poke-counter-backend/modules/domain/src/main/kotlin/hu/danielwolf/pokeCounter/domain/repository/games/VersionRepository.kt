package hu.danielwolf.pokeCounter.domain.repository.games

import hu.danielwolf.pokeCounter.domain.model.games.Version
import org.springframework.data.jpa.repository.JpaRepository

interface VersionRepository : JpaRepository<Version, Int> {
    fun findByName(name: String): Version?
}

