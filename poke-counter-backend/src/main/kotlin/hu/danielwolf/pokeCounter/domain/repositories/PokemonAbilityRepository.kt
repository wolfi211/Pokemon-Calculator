package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entities.PokemonAbility
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonAbilityRepository : JpaRepository<PokemonAbility, Int>

