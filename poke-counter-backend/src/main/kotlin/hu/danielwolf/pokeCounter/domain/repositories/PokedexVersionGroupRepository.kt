package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entity.PokedexVersionGroup
import org.springframework.data.jpa.repository.JpaRepository

interface PokedexVersionGroupRepository : JpaRepository<PokedexVersionGroup, Int>

