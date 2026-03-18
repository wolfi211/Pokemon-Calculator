@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.service.types

import hu.danielwolf.pokeCounter.domain.model.games.Generation
import hu.danielwolf.pokeCounter.domain.model.pokemon.Type
import hu.danielwolf.pokeCounter.domain.model.pokemon.TypeRelation
import hu.danielwolf.pokeCounter.domain.service.games.GenerationService
import hu.danielwolf.pokeCounter.domain.service.pokemon.TypeRelationService
import hu.danielwolf.pokeCounter.domain.service.pokemon.TypeService
import hu.danielwolf.pokeCounter.pokeApi.api.pokemon.PokemonApi
import hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto.ExternalType
import hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto.ExternalTypeRelations
import hu.danielwolf.pokeCounter.pokeApi.config.toEntityMap
import hu.danielwolf.pokeCounter.pokeApi.config.toURI
import java.math.BigDecimal
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class TypesSyncService(
    private val pokemonApi: PokemonApi,
    private val typeService: TypeService,
    private val typeRelationService: TypeRelationService,
    private val generationService: GenerationService,
    private val logger: Logger
) {

    fun syncAll() {
        logger.info("Starting type sync (phase 1: types)...")
        val externalTypes = mutableListOf<ExternalType>()
        val typeSummaries = pokemonApi.getAllTypes(0, 100)
        typeSummaries.results.forEach {
            val external = pokemonApi.followType(it.url.toURI())
            typeService.save(external.toEntity())
            externalTypes.add(external)
        }
        logger.info("Starting type sync (phase 2: type relations)...")
        val relationsByKey = mutableMapOf<String, TypeRelation>()
        externalTypes.forEach { external ->
            addTypeRelations(relationsByKey, external.damageRelations, external.name, null)
            external.pastDamageRelations.forEach { past ->
                val generation = generationService.getByName(past.generation.name)
                addTypeRelations(relationsByKey, past.damageRelations, external.name, generation)
            }
        }
        val relations = relationsByKey.values.toList()
        if (relations.isNotEmpty()) {
            typeRelationService.saveAll(relations)
        }
        logger.info("Finished type sync.")
    }

    private fun addTypeRelations(
        relationsByKey: MutableMap<String, TypeRelation>,
        relations: ExternalTypeRelations,
        fromTypeName: String,
        generation: Generation?
    ) {
        val fromType = typeService.getByName(fromTypeName)
        relations.noDamageTo.forEach { to -> putRelation(relationsByKey, fromType, typeService.getByName(to.name), BigDecimal.ZERO, generation) }
        relations.halfDamageTo.forEach { to -> putRelation(relationsByKey, fromType, typeService.getByName(to.name), BigDecimal("0.5"), generation) }
        relations.doubleDamageTo.forEach { to -> putRelation(relationsByKey, fromType, typeService.getByName(to.name), BigDecimal("2"), generation) }
        relations.noDamageFrom.forEach { from -> putRelation(relationsByKey, typeService.getByName(from.name), fromType, BigDecimal.ZERO, generation) }
        relations.halfDamageFrom.forEach { from -> putRelation(relationsByKey, typeService.getByName(from.name), fromType, BigDecimal("0.5"), generation) }
        relations.doubleDamageFrom.forEach { from -> putRelation(relationsByKey, typeService.getByName(from.name), fromType, BigDecimal("2"), generation) }
    }

    private fun relationKey(damageFrom: Type, damageTo: Type, generation: Generation?): String =
        "${damageFrom.id}_${damageTo.id}_${generation?.id ?: "null"}"

    private fun putRelation(
        relationsByKey: MutableMap<String, TypeRelation>,
        damageFrom: Type,
        damageTo: Type,
        multiplier: BigDecimal,
        generation: Generation?
    ) {
        val key = relationKey(damageFrom, damageTo, generation)
        val existing = relationsByKey[key]
            ?: typeRelationService.findByDamageFromTypeAndDamageToTypeAndGeneration(damageFrom, damageTo, generation)
        if (existing != null) {
            existing.multiplier = multiplier
            if (relationsByKey[key] == null) relationsByKey[key] = existing
        } else {
            relationsByKey[key] = TypeRelation(
                damageFromType = damageFrom,
                damageToType = damageTo,
                multiplier = multiplier,
                generation = generation
            )
        }
    }
}

fun ExternalType.toEntity(): Type = Type(
    id = this.id,
    name = this.name,
    names = this.names.toEntityMap()
)
