package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.model.Version
import org.springframework.data.jpa.repository.JpaRepository

interface VersionRepository : JpaRepository<Version, Int> {
    fun findByName(name: String): Version?
}

