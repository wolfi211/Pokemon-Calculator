package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.entities.Pokedex
import hu.danielwolf.pokeCounter.domain.repositories.PokedexRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PokedexService(
    private val pokedexRepository: PokedexRepository,
) {

    fun getById(id: Int): Pokedex =
        pokedexRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "pokedex.not-found")
        }

    fun getByName(name: String): Pokedex =
        pokedexRepository.findByName(name) ?: throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "pokedex.not-found",
        )

    fun save(pokedex: Pokedex): Pokedex =
        pokedexRepository.save(pokedex)

    fun saveAll(pokedexes: Iterable<Pokedex>): List<Pokedex> =
        pokedexRepository.saveAll(pokedexes)
}

