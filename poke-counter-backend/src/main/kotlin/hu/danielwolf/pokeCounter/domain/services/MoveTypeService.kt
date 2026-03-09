package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.entities.MoveType
import hu.danielwolf.pokeCounter.domain.repositories.MoveTypeRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class MoveTypeService(
    private val moveTypeRepository: MoveTypeRepository,
) {

    fun getById(id: Int): MoveType =
        moveTypeRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "movetype.not-found")
        }

    fun save(moveType: MoveType): MoveType =
        moveTypeRepository.save(moveType)

    fun saveAll(moveTypes: Iterable<MoveType>): List<MoveType> =
        moveTypeRepository.saveAll(moveTypes)
}

