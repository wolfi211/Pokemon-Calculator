package hu.danielwolf.pokeCounter.domain.repository.pokemon

import hu.danielwolf.pokeCounter.domain.model.pokemon.TypeRelation
import org.springframework.data.jpa.repository.JpaRepository

interface TypeRelationRepository : JpaRepository<TypeRelation, Int>

