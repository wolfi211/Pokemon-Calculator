package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entities.PokemonMove
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonMoveRepository : JpaRepository<PokemonMove, Int>

