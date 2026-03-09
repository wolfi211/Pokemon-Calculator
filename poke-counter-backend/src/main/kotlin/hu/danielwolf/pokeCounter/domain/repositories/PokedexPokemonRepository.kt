package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entities.PokedexPokemon
import hu.danielwolf.pokeCounter.domain.entities.PokedexPokemonId
import org.springframework.data.jpa.repository.JpaRepository

interface PokedexPokemonRepository : JpaRepository<PokedexPokemon, PokedexPokemonId>

