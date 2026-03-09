package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.entity.PokemonType
import hu.danielwolf.pokeCounter.domain.entity.PokemonTypeId
import hu.danielwolf.pokeCounter.domain.repositories.PokemonTypeRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PokemonTypeService(
    private val pokemonTypeRepository: PokemonTypeRepository,
) {

    fun getById(id: PokemonTypeId): PokemonType =
        pokemonTypeRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "pokemontype.not-found")
        }

    fun save(pokemonType: PokemonType): PokemonType =
        pokemonTypeRepository.save(pokemonType)

    fun saveAll(pokemonTypes: Iterable<PokemonType>): List<PokemonType> =
        pokemonTypeRepository.saveAll(pokemonTypes)
}

