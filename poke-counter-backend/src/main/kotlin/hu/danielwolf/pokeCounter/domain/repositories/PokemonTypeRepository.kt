package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entity.PokemonType
import hu.danielwolf.pokeCounter.domain.entity.PokemonTypeId
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonTypeRepository : JpaRepository<PokemonType, PokemonTypeId>

