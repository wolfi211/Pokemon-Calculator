package hu.danielwolf.pokeCounter.domain.repository.moves

import hu.danielwolf.pokeCounter.domain.model.moves.Move
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MoveRepository : JpaRepository<Move, Int> {
    fun findByName(name: String): Move?

    /**
     * Search by slug ([Move.name]) and English (`en`) display name in [Move.names] JSON.
     * When [pokemonId] is set, only moves that appear in [pokemon_moves] for that Pokémon.
     */
    @Query(
        value =
            """
        SELECT DISTINCT m.id
        FROM moves m
        WHERE (
            COALESCE(NULLIF(trim(CAST(:searchText AS text)), ''), '') = ''
            OR LOWER(CAST(m.name AS text)) LIKE LOWER(CONCAT('%', CAST(:searchText AS text), '%'))
            OR LOWER(COALESCE(m.names->>'en', '')) LIKE LOWER(CONCAT('%', CAST(:searchText AS text), '%'))
        )
        AND (
            CAST(:pokemonId AS integer) IS NULL
            OR EXISTS (
                SELECT 1 FROM pokemon_moves pm
                WHERE pm.move_id = m.id AND pm.pokemon_id = CAST(:pokemonId AS integer)
            )
        )
        ORDER BY m.id
        """,
        nativeQuery = true,
    )
    fun findIdsForEnglishMoveSearch(
        @Param("searchText") searchText: String?,
        @Param("pokemonId") pokemonId: Int?,
    ): List<Int>

    /**
     * Loads relations needed for minified move search DTOs. Only one `JOIN FETCH` of a plural
     * [Move] association ([Move.types]) per query.
     */
    @Query(
        "SELECT DISTINCT m FROM Move m " +
            "LEFT JOIN FETCH m.damageClass " +
            "LEFT JOIN FETCH m.types mt " +
            "LEFT JOIN FETCH mt.type " +
            "LEFT JOIN FETCH mt.versionGroup " +
            "WHERE m.id IN :ids",
    )
    fun findAllByIdWithMinifiedSearchGraph(
        @Param("ids") ids: Collection<Int>,
    ): List<Move>

    @Query(
        "SELECT DISTINCT m FROM Move m " +
            "LEFT JOIN FETCH m.damageClass " +
            "LEFT JOIN FETCH m.types mt " +
            "LEFT JOIN FETCH mt.type " +
            "LEFT JOIN FETCH mt.versionGroup " +
            "WHERE m.id IN :ids",
    )
    fun findAllByIdWithDamageClassAndTypes(
        @Param("ids") ids: Collection<Int>,
    ): List<Move>
}

