package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entities.MoveType
import org.springframework.data.jpa.repository.JpaRepository

interface MoveTypeRepository : JpaRepository<MoveType, Int>

