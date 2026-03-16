package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.model.VersionGroup
import org.springframework.data.jpa.repository.JpaRepository

interface VersionGroupRepository : JpaRepository<VersionGroup, Int> {
    fun findByName(name: String): VersionGroup?
}

