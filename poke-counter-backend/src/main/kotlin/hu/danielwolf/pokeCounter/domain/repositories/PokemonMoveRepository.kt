package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entity.PokemonMove
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonMoveRepository : JpaRepository<PokemonMove, Int>

