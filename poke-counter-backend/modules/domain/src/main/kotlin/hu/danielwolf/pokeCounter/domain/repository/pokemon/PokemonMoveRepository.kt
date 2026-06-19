package hu.danielwolf.pokeCounter.domain.repository.pokemon

import hu.danielwolf.pokeCounter.domain.model.games.VersionGroup
import hu.danielwolf.pokeCounter.domain.model.moves.Move
import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokemonMove
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PokemonMoveRepository : JpaRepository<PokemonMove, Int> {
    fun findByPokemonAndMoveAndVersionGroup(pokemon: Pokemon, move: Move, versionGroup: VersionGroup): PokemonMove?

    @Query(
        "SELECT pm.move.id, pm.versionGroup.id FROM PokemonMove pm " +
            "WHERE pm.pokemon.id = :pokemonId AND pm.move.id IN :moveIds",
    )
    fun findMoveIdAndVersionGroupIdByPokemonAndMoveIdIn(
        @Param("pokemonId") pokemonId: Int,
        @Param("moveIds") moveIds: Collection<Int>,
    ): List<Array<Any>>

    @Query(
        "SELECT pm FROM PokemonMove pm " +
            "JOIN FETCH pm.move m " +
            "JOIN FETCH m.damageClass " +
            "JOIN FETCH m.types mt " +
            "JOIN FETCH mt.type " +
            "WHERE pm.pokemon.id IN :pokemonIds",
    )
    fun findAllByPokemonIdInWithMoveGraph(
        @Param("pokemonIds") pokemonIds: Collection<Int>,
    ): List<PokemonMove>
}

