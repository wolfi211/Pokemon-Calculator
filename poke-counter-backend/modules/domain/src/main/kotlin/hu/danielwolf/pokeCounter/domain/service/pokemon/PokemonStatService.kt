package hu.danielwolf.pokeCounter.domain.service.pokemon

import hu.danielwolf.pokeCounter.domain.model.games.Generation
import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokemonStat
import hu.danielwolf.pokeCounter.domain.model.pokemon.Stat
import hu.danielwolf.pokeCounter.domain.repository.pokemon.PokemonStatRepository
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

