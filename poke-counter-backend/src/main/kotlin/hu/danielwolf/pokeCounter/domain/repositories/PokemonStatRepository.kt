package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entity.PokemonStat
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonStatRepository : JpaRepository<PokemonStat, Int>

