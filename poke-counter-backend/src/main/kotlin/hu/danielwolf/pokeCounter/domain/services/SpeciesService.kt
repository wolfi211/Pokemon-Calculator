package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.model.Species
import hu.danielwolf.pokeCounter.domain.repositories.SpeciesRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SpeciesService(
    private val speciesRepository: SpeciesRepository,
) {

    fun getById(id: Int): Species =
        speciesRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "species.not-found")
        }

    fun getByName(name: String): Species =
        speciesRepository.findByName(name) ?: throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "species.not-found",
        )

    fun save(species: Species): Species =
        speciesRepository.save(species)

    fun saveAll(species: Iterable<Species>): List<Species> =
        speciesRepository.saveAll(species)
}

