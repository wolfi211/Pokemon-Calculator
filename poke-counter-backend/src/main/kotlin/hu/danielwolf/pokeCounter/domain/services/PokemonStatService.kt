package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.entity.PokemonStat
import hu.danielwolf.pokeCounter.domain.repositories.PokemonStatRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PokemonStatService(
    private val pokemonStatRepository: PokemonStatRepository,
) {

    fun getById(id: Int): PokemonStat =
        pokemonStatRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "pokemonstat.not-found")
        }

    fun save(pokemonStat: PokemonStat): PokemonStat =
        pokemonStatRepository.save(pokemonStat)

    fun saveAll(pokemonStats: Iterable<PokemonStat>): List<PokemonStat> =
        pokemonStatRepository.saveAll(pokemonStats)
}

