@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.APIResource
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalStat(
  val id: Int,
  val name: String,
  @param:JsonProperty("game_index") val gameIndex: Int,
  @param:JsonProperty("is_battle_only") val isBattleOnly: Boolean,
  @param:JsonProperty("affecting_moves") val affectingMoves: ExternalMoveStatAffectSets,
  @param:JsonProperty("affecting_natures") val affectingNatures: ExternalNatureStatAffectSets,
  val characteristics: List<APIResource>,
  @param:JsonProperty("move_damage_class") val moveDamageClass: NamedAPIResource?,
  val names: List<ExternalName>,
)

data class ExternalMoveStatAffectSets(
  val increase: List<ExternalMoveStatAffect>,
  val decrease: List<ExternalMoveStatAffect>,
)

data class ExternalMoveStatAffect(
  val change: Int,
  val move: NamedAPIResource,
)

data class ExternalNatureStatAffectSets(
  val increase: List<NamedAPIResource>,
  val decrease: List<NamedAPIResource>,
)