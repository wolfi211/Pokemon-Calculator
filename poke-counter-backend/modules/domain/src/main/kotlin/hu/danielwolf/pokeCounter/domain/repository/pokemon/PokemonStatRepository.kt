package hu.danielwolf.pokeCounter.domain.repository.pokemon

import hu.danielwolf.pokeCounter.domain.model.games.Generation
import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokemonStat
import hu.danielwolf.pokeCounter.domain.model.pokemon.Stat
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonStatRepository : JpaRepository<PokemonStat, Int> {
    fun findByStatAndPokemonAndGeneration(stat: Stat, pokemon: Pokemon, generation: Generation?): PokemonStat?
}

