package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.model.Move
import hu.danielwolf.pokeCounter.domain.model.Pokemon
import hu.danielwolf.pokeCounter.domain.model.PokemonMove
import hu.danielwolf.pokeCounter.domain.model.VersionGroup
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonMoveRepository : JpaRepository<PokemonMove, Int> {
    fun findByPokemonAndMoveAndVersionGroup(pokemon: Pokemon, move: Move, versionGroup: VersionGroup): PokemonMove?
}

