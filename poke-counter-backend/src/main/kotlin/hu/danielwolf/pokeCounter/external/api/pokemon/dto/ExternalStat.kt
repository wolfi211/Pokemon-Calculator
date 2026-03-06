package hu.danielwolf.pokeCounter.external.api.pokemon.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.APIResource
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalStat(
    val id: Int,
    val name: String,
    val gameIndex: Int,
    val isBattleOnly: Boolean,
    val affectingMoves: ExternalMoveStatAffectSets,
    val affectingNatures: ExternalNatureStatAffectSets,
    val characteristics: List<APIResource>,
    val moveDamageClass: NamedAPIResource,
    val names: List<ExternalName>,
)

@Serializable
data class ExternalMoveStatAffectSets(
    val increase: List<ExternalMoveStatAffect>,
    val decrease: List<ExternalMoveStatAffect>,
)

@Serializable
data class ExternalMoveStatAffect(
    val change: Int,
    val move: NamedAPIResource,
)

@Serializable
data class ExternalNatureStatAffectSets(
    val increase: List<NamedAPIResource>,
    val decrease: List<NamedAPIResource>,
)