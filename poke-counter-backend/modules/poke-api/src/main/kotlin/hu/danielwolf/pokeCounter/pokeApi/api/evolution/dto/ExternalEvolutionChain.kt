@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.evolution.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalEvolutionChain(
  val id: Int,
  @param:JsonProperty("baby_trigger_item") val babyTriggerItem: NamedAPIResource?,
  val chain: ExternalChainLink,
)

data class ExternalChainLink(
  @param:JsonProperty("is_baby") val isBaby: Boolean,
  val species: NamedAPIResource,
  @param:JsonProperty("evolution_details") val evolutionDetails: List<ExternalEvolutionDetail>,
  @param:JsonProperty("evolves_to") val evolvesTo: List<ExternalChainLink>,
)

data class ExternalEvolutionDetail(
  val item: NamedAPIResource?,
  val trigger: NamedAPIResource?,
  val gender: Int?,
  @param:JsonProperty("held_item") val heldItem: NamedAPIResource?,
  @param:JsonProperty("known_move") val knownMove: NamedAPIResource?,
  @param:JsonProperty("known_move_type") val knownMoveType: NamedAPIResource?,
  val location: NamedAPIResource?,
  @param:JsonProperty("min_level") val minLevel: Int?,
  @param:JsonProperty("min_happiness") val minHappiness: Int?,
  @param:JsonProperty("min_beauty") val minBeauty: Int?,
  @param:JsonProperty("min_affection") val minAffection: Int?,
  @param:JsonProperty("needs_multiplayer") val needsMultiplayer: Boolean?,
  @param:JsonProperty("needs_overworld_rain") val needsOverworldRain: Boolean?,
  @param:JsonProperty("party_species") val partySpecies: NamedAPIResource?,
  @param:JsonProperty("party_type") val partyType: NamedAPIResource?,
  @param:JsonProperty("relative_physical_stats") val relativePhysicalStats: Int?,
  @param:JsonProperty("time_of_day") val timeOfDay: String?,
  @param:JsonProperty("trade_species") val tradeSpecies: NamedAPIResource?,
  @param:JsonProperty("turn_upside_down") val turnUpsideDown: Boolean?,
  val region: NamedAPIResource?,
  @param:JsonProperty("base_form") val baseForm: NamedAPIResource?,
  @param:JsonProperty("used_move") val usedMove: NamedAPIResource?,
  @param:JsonProperty("min_move_count") val minMoveCount: Int?,
  @param:JsonProperty("min_steps") val minSteps: Int?,
  @param:JsonProperty("min_damage_taken") val minDamageTaken: Int?,
)