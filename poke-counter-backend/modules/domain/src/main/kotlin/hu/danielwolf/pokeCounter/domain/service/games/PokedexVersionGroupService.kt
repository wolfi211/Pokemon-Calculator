package hu.danielwolf.pokeCounter.domain.service.games

import hu.danielwolf.pokeCounter.domain.model.games.PokedexVersionGroup
import hu.danielwolf.pokeCounter.domain.repository.games.PokedexVersionGroupRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PokedexVersionGroupService(
    private val pokedexVersionGroupRepository: PokedexVersionGroupRepository,
) {

    fun getById(id: Int): PokedexVersionGroup =
        pokedexVersionGroupRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "pokedexversiongroup.not-found")
        }

    fun save(pokedexVersionGroup: PokedexVersionGroup): PokedexVersionGroup =
        pokedexVersionGroupRepository.save(pokedexVersionGroup)

    fun saveAll(pokedexVersionGroups: Iterable<PokedexVersionGroup>): List<PokedexVersionGroup> =
        pokedexVersionGroupRepository.saveAll(pokedexVersionGroups)

    fun findAll(): List<PokedexVersionGroup> = pokedexVersionGroupRepository.findAll()

    /** Loads all with pokedex and versionGroup eagerly (avoids LazyInitializationException in long-running loops). */
    fun findAllWithPokedexAndVersionGroup(): List<PokedexVersionGroup> =
        pokedexVersionGroupRepository.findAllWithPokedexAndVersionGroup()
}

