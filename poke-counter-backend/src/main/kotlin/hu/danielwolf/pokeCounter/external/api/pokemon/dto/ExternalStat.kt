package hu.danielwolf.pokeCounter.external.api.pokemon.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.APIResource
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalStat(
    val id: Int,
    val name: String,
    @JsonProperty("game_index") val gameIndex: Int,
    @JsonProperty("is_battle_only") val isBattleOnly: Boolean,
    @JsonProperty("affecting_moves") val affectingMoves: ExternalMoveStatAffectSets,
    @JsonProperty("affecting_natures") val affectingNatures: ExternalNatureStatAffectSets,
    val characteristics: List<APIResource>,
    @JsonProperty("move_damage_class") val moveDamageClass: NamedAPIResource?,
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