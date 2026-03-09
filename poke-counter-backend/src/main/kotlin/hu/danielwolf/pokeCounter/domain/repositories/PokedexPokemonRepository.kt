package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entity.PokedexPokemon
import hu.danielwolf.pokeCounter.domain.entity.PokedexPokemonId
import org.springframework.data.jpa.repository.JpaRepository

interface PokedexPokemonRepository : JpaRepository<PokedexPokemon, PokedexPokemonId>

