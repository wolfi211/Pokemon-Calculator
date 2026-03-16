package hu.danielwolf.pokeCounter.external.api.evolution.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalEvolutionChain(
    val id: Int,
    @JsonProperty("baby_trigger_item") val babyTriggerItem: NamedAPIResource?,
    val chain: ExternalChainLink,
)

data class ExternalChainLink(
    @JsonProperty("is_baby") val isBaby: Boolean,
    val species: NamedAPIResource,
    @JsonProperty("evolution_details") val evolutionDetails: List<ExternalEvolutionDetail>,
    @JsonProperty("evolves_to") val evolvesTo: List<ExternalChainLink>,
)

data class ExternalEvolutionDetail(
    val item: NamedAPIResource?,
    val trigger: NamedAPIResource?,
    val gender: Int?,
    @JsonProperty("held_item") val heldItem: NamedAPIResource?,
    @JsonProperty("known_move") val knownMove: NamedAPIResource?,
    @JsonProperty("known_move_type") val knownMoveType: NamedAPIResource?,
    val location: NamedAPIResource?,
    @JsonProperty("min_level") val minLevel: Int?,
    @JsonProperty("min_happiness") val minHappiness: Int?,
    @JsonProperty("min_beauty") val minBeauty: Int?,
    @JsonProperty("min_affection") val minAffection: Int?,
    @JsonProperty("needs_multiplayer") val needsMultiplayer: Boolean?,
    @JsonProperty("needs_overworld_rain") val needsOverworldRain: Boolean?,
    @JsonProperty("party_species") val partySpecies: NamedAPIResource?,
    @JsonProperty("party_type") val partyType: NamedAPIResource?,
    @JsonProperty("relative_physical_stats") val relativePhysicalStats: Int?,
    @JsonProperty("time_of_day") val timeOfDay: String?,
    @JsonProperty("trade_species") val tradeSpecies: NamedAPIResource?,
    @JsonProperty("turn_upside_down") val turnUpsideDown: Boolean?,
    val region: NamedAPIResource?,
    @JsonProperty("base_form") val baseForm: NamedAPIResource?,
    @JsonProperty("used_move") val usedMove: NamedAPIResource?,
    @JsonProperty("min_move_count") val minMoveCount: Int?,
    @JsonProperty("min_steps") val minSteps: Int?,
    @JsonProperty("min_damage_taken") val minDamageTaken: Int?,
)