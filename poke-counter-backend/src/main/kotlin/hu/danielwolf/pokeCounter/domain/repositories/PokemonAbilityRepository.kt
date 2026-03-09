package hu.danielwolf.pokeCounter.domain.repositories

import hu.danielwolf.pokeCounter.domain.entity.PokemonAbility
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonAbilityRepository : JpaRepository<PokemonAbility, Int>

