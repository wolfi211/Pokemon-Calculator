package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.entity.Generation
import hu.danielwolf.pokeCounter.domain.repositories.GenerationRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class GenerationService(
    private val generationRepository: GenerationRepository,
) {

    fun getById(id: Int): Generation =
        generationRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "generation.not-found")
        }

    fun getByName(name: String): Generation =
        generationRepository.findByName(name) ?: throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "generation.not-found",
        )

    fun getAll(): List<Generation> =
        generationRepository.findAll()

    fun save(generation: Generation): Generation =
        generationRepository.save(generation)

    fun saveAll(generations: Iterable<Generation>): List<Generation> =
        generationRepository.saveAll(generations)
}

