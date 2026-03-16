package hu.danielwolf.pokeCounter.domain.repository.pokemon

import hu.danielwolf.pokeCounter.domain.model.games.Generation
import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokemonAbility
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonAbilityRepository : JpaRepository<PokemonAbility, Int> {
    fun findByPokemonAndSlotAndGeneration(pokemon: Pokemon, slot: Int, generation: Generation?): PokemonAbility?
}

