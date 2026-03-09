package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entities.PokedexVersionGroup
import org.springframework.data.jpa.repository.JpaRepository

interface PokedexVersionGroupRepository : JpaRepository<PokedexVersionGroup, Int>

