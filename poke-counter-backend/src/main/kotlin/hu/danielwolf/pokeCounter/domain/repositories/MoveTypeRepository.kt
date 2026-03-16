package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.model.MoveType
import org.springframework.data.jpa.repository.JpaRepository

interface MoveTypeRepository : JpaRepository<MoveType, Int>

