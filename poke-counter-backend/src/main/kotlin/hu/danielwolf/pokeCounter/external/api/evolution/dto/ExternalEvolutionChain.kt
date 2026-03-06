package hu.danielwolf.pokeCounter.external.api.evolution.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalEvolutionChain(
    val id: Int,
    val babyTriggerItem: NamedAPIResource,
    val chain: ExternalChainLink,
)

@Serializable
data class ExternalChainLink(
    val isBaby: Boolean,
    val species: NamedAPIResource,
    val evolutionDetails: List<ExternalEvolutionDetail>,
    val evolvesTo: List<ExternalChainLink>,
)

@Serializable
data class ExternalEvolutionDetail(
    val item: NamedAPIResource,
    val trigger: NamedAPIResource,
    val gender: Int,
    val heldItem: NamedAPIResource,
    val knownMove: NamedAPIResource,
    val knownMoveType: NamedAPIResource,
    val location: NamedAPIResource,
    val minLevel: Int,
    val minHappiness: Int,
    val minBeauty: Int,
    val minAffection: Int,
    val needsMultiplayer: Boolean,
    val needsOverworldRain: Boolean,
    val partySpecies: NamedAPIResource,
    val partyType: NamedAPIResource,
    val relativePhysicalStats: Int,
    val timeOfDay: String,
    val tradeSpecies: NamedAPIResource,
    val turnUpsideDown: Boolean,
    val region: NamedAPIResource,
    val baseForm: NamedAPIResource,
    val usedMove: NamedAPIResource,
    val minMoveCount: Int,
    val minSteps: Int,
    val minDamageTaken: Int,
)
