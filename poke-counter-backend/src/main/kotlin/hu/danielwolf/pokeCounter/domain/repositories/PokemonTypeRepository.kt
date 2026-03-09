package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entities.PokemonType
import hu.danielwolf.pokeCounter.domain.entities.PokemonTypeId
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonTypeRepository : JpaRepository<PokemonType, PokemonTypeId>

