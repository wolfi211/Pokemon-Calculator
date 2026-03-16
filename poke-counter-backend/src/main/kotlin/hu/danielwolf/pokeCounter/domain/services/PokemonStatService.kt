package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.model.Generation
import hu.danielwolf.pokeCounter.domain.model.Pokemon
import hu.danielwolf.pokeCounter.domain.model.PokemonStat
import hu.danielwolf.pokeCounter.domain.model.Stat
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

    fun getByStatAndPokemonAndGeneration(stat: Stat, pokemon: Pokemon, generation: Generation? = null): PokemonStat? =
        pokemonStatRepository.findByStatAndPokemonAndGeneration(stat, pokemon, generation)
}

