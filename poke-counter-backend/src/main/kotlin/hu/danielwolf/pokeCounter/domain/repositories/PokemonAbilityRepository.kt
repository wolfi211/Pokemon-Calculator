package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.model.Generation
import hu.danielwolf.pokeCounter.domain.model.Pokemon
import hu.danielwolf.pokeCounter.domain.model.PokemonAbility
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonAbilityRepository : JpaRepository<PokemonAbility, Int> {
    fun findByPokemonAndSlotAndGeneration(pokemon: Pokemon, slot: Int, generation: Generation?): PokemonAbility?
}

