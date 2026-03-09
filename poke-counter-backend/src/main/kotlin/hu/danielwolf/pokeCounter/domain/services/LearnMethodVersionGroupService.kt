package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.entity.LearnMethodVersionGroup
import hu.danielwolf.pokeCounter.domain.repositories.LearnMethodVersionGroupRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class LearnMethodVersionGroupService(
    private val learnMethodVersionGroupRepository: LearnMethodVersionGroupRepository,
) {

    fun getById(id: Int): LearnMethodVersionGroup =
        learnMethodVersionGroupRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "learnmethodversiongroup.not-found")
        }

    fun save(learnMethodVersionGroup: LearnMethodVersionGroup): LearnMethodVersionGroup =
        learnMethodVersionGroupRepository.save(learnMethodVersionGroup)

    fun saveAll(learnMethodVersionGroups: Iterable<LearnMethodVersionGroup>): List<LearnMethodVersionGroup> =
        learnMethodVersionGroupRepository.saveAll(learnMethodVersionGroups)
}

