package hu.danielwolf.pokeCounter.domain.repository.pokemon

import hu.danielwolf.pokeCounter.domain.model.games.Generation
import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokemonType
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonTypeRepository : JpaRepository<PokemonType, Int> {
  fun findBySlotAndPokemonAndGeneration(slot: Int, pokemon: Pokemon, generation: Generation?): PokemonType?
}

