package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entities.TypeRelation
import org.springframework.data.jpa.repository.JpaRepository

interface TypeRelationRepository : JpaRepository<TypeRelation, Int>

