package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.entities.Ability
import hu.danielwolf.pokeCounter.domain.repositories.AbilityRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class AbilityService(
    private val abilityRepository: AbilityRepository,
) {

    fun getById(id: Int): Ability =
        abilityRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "ability.not-found")
        }

    fun getByName(name: String): Ability =
        abilityRepository.findByName(name) ?: throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "ability.not-found",
        )

    fun save(ability: Ability): Ability =
        abilityRepository.save(ability)

    fun saveAll(abilities: Iterable<Ability>): List<Ability> =
        abilityRepository.saveAll(abilities)
}

