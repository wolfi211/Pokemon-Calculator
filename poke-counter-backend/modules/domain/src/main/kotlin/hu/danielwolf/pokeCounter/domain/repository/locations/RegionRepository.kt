package hu.danielwolf.pokeCounter.domain.repository.locations

import hu.danielwolf.pokeCounter.domain.model.locations.Region
import org.springframework.data.jpa.repository.JpaRepository

interface RegionRepository : JpaRepository<Region, Int> {
    fun findByName(name: String): Region?
}