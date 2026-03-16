package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.model.PokemonForm
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PokemonFormRepository : JpaRepository<PokemonForm, Int> {
    fun findByName(name: String): PokemonForm?
    fun findByVersionGroup_Id(versionGroupId: Int): List<PokemonForm>

    @Query(
        """
        SELECT DISTINCT f FROM PokemonForm f
        LEFT JOIN FETCH f.pokemon p
        LEFT JOIN FETCH p.species
        LEFT JOIN FETCH f.versionGroup
        WHERE f.isMega = true OR f.formName = 'gmax'
        """
    )
    fun findAllMegaAndGmaxWithPokemonAndVersionGroup(): List<PokemonForm>
}

