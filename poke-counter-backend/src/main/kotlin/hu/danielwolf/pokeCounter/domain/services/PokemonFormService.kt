package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.entities.PokemonForm
import hu.danielwolf.pokeCounter.domain.repositories.PokemonFormRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PokemonFormService(
    private val pokemonFormRepository: PokemonFormRepository,
) {

    fun getById(id: Int): PokemonForm =
        pokemonFormRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "pokemonform.not-found")
        }

    fun getByName(name: String): PokemonForm =
        pokemonFormRepository.findByName(name) ?: throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "pokemonform.not-found",
        )

    fun save(pokemonForm: PokemonForm): PokemonForm =
        pokemonFormRepository.save(pokemonForm)

    fun saveAll(pokemonForms: Iterable<PokemonForm>): List<PokemonForm> =
        pokemonFormRepository.saveAll(pokemonForms)
}

