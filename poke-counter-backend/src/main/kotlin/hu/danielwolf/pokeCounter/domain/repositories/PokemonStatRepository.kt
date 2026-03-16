package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.model.Generation
import hu.danielwolf.pokeCounter.domain.model.Pokemon
import hu.danielwolf.pokeCounter.domain.model.PokemonStat
import hu.danielwolf.pokeCounter.domain.model.Stat
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonStatRepository : JpaRepository<PokemonStat, Int> {
    fun findByStatAndPokemonAndGeneration(stat: Stat, pokemon: Pokemon, generation: Generation?): PokemonStat?
}

