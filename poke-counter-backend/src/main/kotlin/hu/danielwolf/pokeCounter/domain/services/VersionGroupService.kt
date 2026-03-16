package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.model.VersionGroup
import hu.danielwolf.pokeCounter.domain.repositories.VersionGroupRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class VersionGroupService(
    private val versionGroupRepository: VersionGroupRepository,
) {

    fun getById(id: Int): VersionGroup =
        versionGroupRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "versiongroup.not-found")
        }

    fun getByName(name: String): VersionGroup =
        versionGroupRepository.findByName(name) ?: throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "versiongroup.not-found",
        )

    fun save(versionGroup: VersionGroup): VersionGroup =
        versionGroupRepository.save(versionGroup)

    fun saveAll(versionGroups: Iterable<VersionGroup>): List<VersionGroup> =
        versionGroupRepository.saveAll(versionGroups)

    fun findAll(): List<VersionGroup> = versionGroupRepository.findAll()
}

