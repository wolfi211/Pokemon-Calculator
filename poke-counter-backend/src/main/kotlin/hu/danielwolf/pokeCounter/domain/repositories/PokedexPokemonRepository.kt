package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.model.PokedexPokemon
import hu.danielwolf.pokeCounter.domain.model.PokedexPokemonId
import org.springframework.data.jpa.repository.JpaRepository

interface PokedexPokemonRepository : JpaRepository<PokedexPokemon, PokedexPokemonId>

