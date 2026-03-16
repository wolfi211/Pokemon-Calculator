package hu.danielwolf.pokeCounter.domain.service.pokemon

import hu.danielwolf.pokeCounter.domain.model.pokemon.Type
import hu.danielwolf.pokeCounter.domain.repository.pokemon.TypeRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class TypeService(
    private val typeRepository: TypeRepository,
) {

    fun getById(id: Int): Type =
        typeRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "type.not-found")
        }

    fun getByName(name: String): Type =
        typeRepository.findByName(name) ?: throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "type.not-found",
        )

    fun getAll(): List<Type> =
        typeRepository.findAll()

    fun save(type: Type): Type =
        typeRepository.save(type)

    fun saveAll(types: Iterable<Type>): List<Type> =
        typeRepository.saveAll(types)
}

