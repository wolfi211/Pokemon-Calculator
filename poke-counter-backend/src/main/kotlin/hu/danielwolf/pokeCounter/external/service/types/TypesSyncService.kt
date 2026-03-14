package hu.danielwolf.pokeCounter.external.service.types

import hu.danielwolf.pokeCounter.domain.entities.Generation
import hu.danielwolf.pokeCounter.domain.entities.Type
import hu.danielwolf.pokeCounter.domain.entities.TypeRelation
import hu.danielwolf.pokeCounter.domain.services.GenerationService
import hu.danielwolf.pokeCounter.domain.services.TypeRelationService
import hu.danielwolf.pokeCounter.domain.services.TypeService
import hu.danielwolf.pokeCounter.external.api.pokemon.PokemonApi
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalType
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalTypeRelations
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import hu.danielwolf.pokeCounter.external.api.utilities.dto.PageRequest
import hu.danielwolf.pokeCounter.external.config.toEntityMap
import hu.danielwolf.pokeCounter.external.config.toURI
import org.slf4j.Logger
import org.springframework.stereotype.Service
import java.math.BigDecimal

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
        val typeSummaries = pokemonApi.getAllTypes(PageRequest(0, 100))
        typeSummaries.results.forEach {
            try {
                val external = pokemonApi.followType(it.url.toURI())
                typeService.save(external.toEntity())
                externalTypes.add(external)
            } catch (e: Exception) {
                logger.error("Error syncing type ${it.name}: ${e.message}")
            }
        }
        logger.info("Starting type sync (phase 2: type relations)...")
        val relations = mutableListOf<TypeRelation>()
        externalTypes.forEach { external ->
            try {
                relations.addAll(buildTypeRelations(external.damageRelations, external.name, null))
                external.pastDamageRelations.forEach { past ->
                    val generation = generationService.getByName(past.generation.name)
                    relations.addAll(buildTypeRelations(past.damageRelation, external.name, generation))
                }
            } catch (e: Exception) {
                logger.error("Error building type relations for ${external.name}: ${e.message}")
            }
        }
        if (relations.isNotEmpty()) {
            typeRelationService.saveAll(relations)
        }
        logger.info("Finished type sync.")
    }

    private fun buildTypeRelations(
        relations: ExternalTypeRelations,
        fromTypeName: String,
        generation: Generation?
    ): List<TypeRelation> {
        val fromType = typeService.getByName(fromTypeName)
        val result = mutableListOf<TypeRelation>()
        relations.noDamageTo.forEach { to -> result.add(relation(fromType, typeService.getByName(to.name), BigDecimal.ZERO, generation)) }
        relations.halfDamageTo.forEach { to -> result.add(relation(fromType, typeService.getByName(to.name), BigDecimal("0.5"), generation)) }
        relations.doubleDamageTo.forEach { to -> result.add(relation(fromType, typeService.getByName(to.name), BigDecimal("2"), generation)) }
        relations.noDamageFrom.forEach { from -> result.add(relation(typeService.getByName(from.name), fromType, BigDecimal.ZERO, generation)) }
        relations.halfDamageFrom.forEach { from -> result.add(relation(typeService.getByName(from.name), fromType, BigDecimal("0.5"), generation)) }
        relations.doubleDamageFrom.forEach { from -> result.add(relation(typeService.getByName(from.name), fromType, BigDecimal("2"), generation)) }
        return result
    }

    private fun relation(damageFrom: Type, damageTo: Type, multiplier: BigDecimal, generation: Generation?): TypeRelation =
        TypeRelation(
            damageFromType = damageFrom,
            damageToType = damageTo,
            multiplier = multiplier,
            generation = generation
        )
}

fun ExternalType.toEntity(): Type = Type(
    id = this.id,
    name = this.name,
    names = this.names.toEntityMap()
)
