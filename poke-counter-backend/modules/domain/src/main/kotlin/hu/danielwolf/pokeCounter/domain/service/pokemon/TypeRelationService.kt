package hu.danielwolf.pokeCounter.domain.service.pokemon

import hu.danielwolf.pokeCounter.domain.model.games.Generation
import hu.danielwolf.pokeCounter.domain.model.pokemon.Type
import hu.danielwolf.pokeCounter.domain.model.pokemon.TypeRelation
import hu.danielwolf.pokeCounter.domain.repository.pokemon.TypeRelationRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class TypeRelationService(
    private val typeRelationRepository: TypeRelationRepository,
) {

    fun getById(id: Int): TypeRelation =
        typeRelationRepository.findById(id).orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "typerelation.not-found")
        }

    fun save(typeRelation: TypeRelation): TypeRelation =
        typeRelationRepository.save(typeRelation)

    fun saveAll(typeRelations: Iterable<TypeRelation>): List<TypeRelation> =
        typeRelationRepository.saveAll(typeRelations)

    fun findByDamageFromTypeAndDamageToTypeAndGeneration(
        damageFromType: Type,
        damageToType: Type,
        generation: Generation?,
    ): TypeRelation? =
        typeRelationRepository.findByDamageFromTypeAndDamageToTypeAndGeneration(damageFromType, damageToType, generation)
}

