package hu.danielwolf.pokeCounter.domain.services

import hu.danielwolf.pokeCounter.domain.entity.TypeRelation
import hu.danielwolf.pokeCounter.domain.repositories.TypeRelationRepository
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
}

