package hu.danielwolf.pokeCounter.domain.service.pokemon

import hu.danielwolf.pokeCounter.domain.model.games.Generation
import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokemonAbility
import hu.danielwolf.pokeCounter.domain.repository.pokemon.PokemonAbilityRepository
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

    fun getByPokemonAndSlotAndGeneration(pokemon: Pokemon, slot: Int, generation: Generation?): PokemonAbility? =
        pokemonAbilityRepository.findByPokemonAndSlotAndGeneration(pokemon, slot, generation)
}

