package hu.danielwolf.pokeCounter.domain.repository.pokemon

import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PokemonRepository : JpaRepository<Pokemon, Int> {
    fun findByName(name: String): Pokemon?
    fun findBySpeciesId(speciesId: Int): List<Pokemon>

    /**
     * Search by slug ([Pokemon.name]) and by English (`en`) display name in related [Species.names] JSON only.
     * Empty or null [searchText] matches all rows (subject to [versionGroup] filter).
     * [versionGroup] null: no pokedex filter; non-null: pokemon must appear in [pokedex_pokemon] for that version group.
     */
    @Query(
        value =
            """
        SELECT DISTINCT p.id
        FROM pokemon p
        INNER JOIN species s ON p.species_id = s.id
        WHERE (
            COALESCE(NULLIF(trim(CAST(:searchText AS text)), ''), '') = ''
            OR LOWER(CAST(p.name AS text)) LIKE LOWER(CONCAT('%', CAST(:searchText AS text), '%'))
            OR LOWER(COALESCE(s.names->>'en', '')) LIKE LOWER(CONCAT('%', CAST(:searchText AS text), '%'))
        )
        AND (
            CAST(:versionGroup AS integer) IS NULL
            OR EXISTS (
                SELECT 1 FROM pokedex_pokemon pp
                WHERE pp.pokemon_id = p.id AND pp.version_group_id = CAST(:versionGroup AS integer)
            )
        )
        ORDER BY p.id
        """,
        nativeQuery = true,
    )
    fun findIdsForLocalizedSearch(
        @Param("searchText") searchText: String?,
        @Param("versionGroup") versionGroup: Int?,
    ): List<Int>

    /**
     * Minified search needs [Pokemon.species], [Pokemon.forms], [Pokemon.types], and [PokemonType.type].
     * A single query must not `JOIN FETCH` more than one plural [Pokemon] association (e.g. both
     * `forms` and `types`), or Hibernate 7 can throw [java.util.ConcurrentModificationException]
     * while merging rows. [org.hibernate.annotations.BatchSize] on those collections can trigger the
     * same class of bug during batched lazy loads.
     */
    @Query(
        "SELECT DISTINCT p FROM Pokemon p LEFT JOIN FETCH p.species LEFT JOIN FETCH p.forms WHERE p.id IN :ids",
    )
    fun findAllByIdWithSpeciesAndForms(
        @Param("ids") ids: Collection<Int>,
    ): List<Pokemon>

    @Query(
        "SELECT DISTINCT p FROM Pokemon p LEFT JOIN FETCH p.types pt LEFT JOIN FETCH pt.type WHERE p.id IN :ids",
    )
    fun findAllByIdWithTypesAndTypeEntity(
        @Param("ids") ids: Collection<Int>,
    ): List<Pokemon>

    @Query(
        "SELECT DISTINCT p FROM Pokemon p " +
            "LEFT JOIN FETCH p.stats ps LEFT JOIN FETCH ps.stat " +
            "WHERE p.id IN :ids AND (ps.generation IS NULL OR ps.id IS NULL)",
    )
    fun findAllByIdWithCurrentStats(
        @Param("ids") ids: Collection<Int>,
    ): List<Pokemon>

    @Query(
        "SELECT DISTINCT p FROM Pokemon p " +
            "LEFT JOIN FETCH p.types pt LEFT JOIN FETCH pt.type " +
            "LEFT JOIN FETCH p.stats ps LEFT JOIN FETCH ps.stat " +
            "WHERE ps.generation IS NULL OR ps.id IS NULL",
    )
    fun findAllWithTypesAndCurrentStats(): List<Pokemon>
}
