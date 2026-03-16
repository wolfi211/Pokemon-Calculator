package hu.danielwolf.pokeCounter.domain.repository.moves

import hu.danielwolf.pokeCounter.domain.model.moves.MoveType
import org.springframework.data.jpa.repository.JpaRepository

interface MoveTypeRepository : JpaRepository<MoveType, Int>

