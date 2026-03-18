package hu.danielwolf.pokeCounter.domain.repository.games

import hu.danielwolf.pokeCounter.domain.model.games.Pokedex
import hu.danielwolf.pokeCounter.domain.model.games.PokedexVersionGroup
import hu.danielwolf.pokeCounter.domain.model.games.VersionGroup
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PokedexVersionGroupRepository : JpaRepository<PokedexVersionGroup, Int> {

    @Query("SELECT pvg FROM PokedexVersionGroup pvg JOIN FETCH pvg.pokedex JOIN FETCH pvg.versionGroup")
    fun findAllWithPokedexAndVersionGroup(): List<PokedexVersionGroup>

    fun findByPokedexAndVersionGroup(pokedex: Pokedex, versionGroup: VersionGroup): PokedexVersionGroup?
}

