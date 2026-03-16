package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.model.PokedexVersionGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PokedexVersionGroupRepository : JpaRepository<PokedexVersionGroup, Int> {

    @Query("SELECT pvg FROM PokedexVersionGroup pvg JOIN FETCH pvg.pokedex JOIN FETCH pvg.versionGroup")
    fun findAllWithPokedexAndVersionGroup(): List<PokedexVersionGroup>
}

