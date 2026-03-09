package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.entity.PokemonAbility
import hu.danielwolf.pokeCounter.domain.repositories.PokemonAbilityRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PokemonAbilityService(
    private val pokemonAbilityRepository: PokemonAbilityRepository,
) {

    fun getById(id: Int): PokemonAbility =
        pokemonAbilityRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "pokemonability.not-found")
        }

    fun save(pokemonAbility: PokemonAbility): PokemonAbility =
        pokemonAbilityRepository.save(pokemonAbility)

    fun saveAll(pokemonAbilities: Iterable<PokemonAbility>): List<PokemonAbility> =
        pokemonAbilityRepository.saveAll(pokemonAbilities)
}

