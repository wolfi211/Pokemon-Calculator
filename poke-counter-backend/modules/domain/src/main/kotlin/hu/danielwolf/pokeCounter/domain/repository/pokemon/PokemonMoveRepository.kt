package hu.danielwolf.pokeCounter.domain.repository.pokemon

import hu.danielwolf.pokeCounter.domain.model.games.VersionGroup
import hu.danielwolf.pokeCounter.domain.model.moves.Move
import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokemonMove
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonMoveRepository : JpaRepository<PokemonMove, Int> {
    fun findByPokemonAndMoveAndVersionGroup(pokemon: Pokemon, move: Move, versionGroup: VersionGroup): PokemonMove?
}

