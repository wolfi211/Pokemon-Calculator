package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entity.Region
import org.springframework.data.jpa.repository.JpaRepository

interface RegionRepository : JpaRepository<Region, Int> {
    fun findByName(name: String): Region?
}

