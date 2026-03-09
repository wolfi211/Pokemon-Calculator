package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.entity.Region
import hu.danielwolf.pokeCounter.domain.repositories.RegionRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class RegionService(
    private val regionRepository: RegionRepository,
) {

    fun getById(id: Int): Region =
        regionRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "region.not-found")
        }

    fun getByName(name: String): Region =
        regionRepository.findByName(name) ?: throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "region.not-found",
        )

    fun getAll(): List<Region> =
        regionRepository.findAll()

    fun save(region: Region): Region =
        regionRepository.save(region)

    fun saveAll(regions: Iterable<Region>): List<Region> =
        regionRepository.saveAll(regions)
}

