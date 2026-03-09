package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.entity.DamageClass
import hu.danielwolf.pokeCounter.domain.repositories.DamageClassRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DamageClassService(
    private val damageClassRepository: DamageClassRepository,
) {

    fun getById(id: Int): DamageClass =
        damageClassRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "damageclass.not-found")
        }

    fun getByName(name: String): DamageClass =
        damageClassRepository.findByName(name) ?: throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "damageclass.not-found",
        )

    fun getAll(): List<DamageClass> =
        damageClassRepository.findAll()

    fun save(damageClass: DamageClass): DamageClass =
        damageClassRepository.save(damageClass)

    fun saveAll(damageClasses: Iterable<DamageClass>): List<DamageClass> =
        damageClassRepository.saveAll(damageClasses)
}

