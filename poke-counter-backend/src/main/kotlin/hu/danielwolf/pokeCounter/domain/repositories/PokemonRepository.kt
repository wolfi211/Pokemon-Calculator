package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.model.Pokemon
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonRepository : JpaRepository<Pokemon, Int> {
    fun findByName(name: String): Pokemon?
    fun findBySpecies_Id(speciesId: Int): List<Pokemon>
}

