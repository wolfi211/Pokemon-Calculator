package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.entity.Version
import hu.danielwolf.pokeCounter.domain.repositories.VersionRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class VersionService(
    private val versionRepository: VersionRepository,
) {

    fun getById(id: Int): Version =
        versionRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "version.not-found")
        }

    fun getByName(name: String): Version =
        versionRepository.findByName(name) ?: throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "version.not-found",
        )

    fun save(version: Version): Version =
        versionRepository.save(version)

    fun saveAll(versions: Iterable<Version>): List<Version> =
        versionRepository.saveAll(versions)
}

