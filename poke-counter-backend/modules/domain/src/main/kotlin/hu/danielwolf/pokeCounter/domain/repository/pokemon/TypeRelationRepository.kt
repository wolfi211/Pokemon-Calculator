package hu.danielwolf.pokeCounter.domain.repository.pokemon

import hu.danielwolf.pokeCounter.domain.model.games.Generation
import hu.danielwolf.pokeCounter.domain.model.pokemon.Type
import hu.danielwolf.pokeCounter.domain.model.pokemon.TypeRelation
import org.springframework.data.jpa.repository.JpaRepository

interface TypeRelationRepository : JpaRepository<TypeRelation, Int> {
    fun findByDamageFromTypeAndDamageToTypeAndGeneration(
        damageFromType: Type,
        damageToType: Type,
        generation: Generation?,
    ): TypeRelation?
}

