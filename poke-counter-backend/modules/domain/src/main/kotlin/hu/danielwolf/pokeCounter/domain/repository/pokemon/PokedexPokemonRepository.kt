package hu.danielwolf.pokeCounter.domain.repository.pokemon

import hu.danielwolf.pokeCounter.domain.model.pokemon.PokedexPokemon
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokedexPokemonId
import org.springframework.data.jpa.repository.JpaRepository

interface PokedexPokemonRepository : JpaRepository<PokedexPokemon, PokedexPokemonId>

