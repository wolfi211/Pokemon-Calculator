package hu.danielwolf.pokeCounter.domain.service.moves

import hu.danielwolf.pokeCounter.domain.model.moves.MoveLearnMethod
import hu.danielwolf.pokeCounter.domain.repository.moves.MoveLearnMethodRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class MoveLearnMethodService(
    private val moveLearnMethodRepository: MoveLearnMethodRepository,
) {

    fun getById(id: Int): MoveLearnMethod =
        moveLearnMethodRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "movelearnmethod.not-found")
        }

    fun getByName(name: String): MoveLearnMethod =
        moveLearnMethodRepository.findByName(name) ?: throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "movelearnmethod.not-found",
        )

    fun save(moveLearnMethod: MoveLearnMethod): MoveLearnMethod =
        moveLearnMethodRepository.save(moveLearnMethod)

    fun saveAll(moveLearnMethods: Iterable<MoveLearnMethod>): List<MoveLearnMethod> =
        moveLearnMethodRepository.saveAll(moveLearnMethods)
}

