package hu.danielwolf.pokeCounter.domain.service.pokemon

import hu.danielwolf.pokeCounter.domain.model.pokemon.Stat
import hu.danielwolf.pokeCounter.domain.repository.pokemon.StatRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class StatService(
    private val statRepository: StatRepository,
) {

    fun getById(id: Int): Stat =
        statRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "stat.not-found")
        }

    fun getByName(name: String): Stat =
        statRepository.findByName(name) ?: throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "stat.not-found",
        )

    fun getAll(): List<Stat> =
        statRepository.findAll()

    fun save(stat: Stat): Stat =
        statRepository.save(stat)

    fun saveAll(stats: Iterable<Stat>): List<Stat> =
        statRepository.saveAll(stats)
}

