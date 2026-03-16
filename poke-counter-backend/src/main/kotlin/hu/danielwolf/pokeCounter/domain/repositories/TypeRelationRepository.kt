package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.model.TypeRelation
import org.springframework.data.jpa.repository.JpaRepository

interface TypeRelationRepository : JpaRepository<TypeRelation, Int>

