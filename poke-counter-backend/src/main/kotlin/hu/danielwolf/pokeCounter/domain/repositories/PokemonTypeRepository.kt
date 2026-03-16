package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.model.Generation
import hu.danielwolf.pokeCounter.domain.model.Pokemon
import hu.danielwolf.pokeCounter.domain.model.PokemonType
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonTypeRepository : JpaRepository<PokemonType, Int> {
  fun findBySlotAndPokemonAndGeneration(slot: Int, pokemon: Pokemon, generation: Generation?): PokemonType?
}

