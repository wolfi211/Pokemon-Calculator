package hu.danielwolf.pokeCounter.external.api.move.dto

import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalAbilityEffectChange
import hu.danielwolf.pokeCounter.external.api.utilities.dto.APIResource
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalMachineVersionDetail
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalVerboseEffect
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalMove(
    val id: Int,
    val name: String,
    val accuracy: Int,
    val effectChance: Int,
    val pp: Int,
    val priority: Int,
    val power: Int,
    val contestCombos: ExternalContestComboSets,
    val contestType: NamedAPIResource,
    val contestEffect: APIResource,
    val damageClass: NamedAPIResource,
    val effectEntries: List<ExternalVerboseEffect>,
    val effectChanges: List<ExternalAbilityEffectChange>,
    val learnedByPokemon: List<NamedAPIResource>,
    val flavorTestEntries: List<ExternalMoveFlavorText>,
    val generation: NamedAPIResource,
    val machines: List<ExternalMachineVersionDetail>,
    val meta: ExternalMoveMetaData,
    val names: List<ExternalName>,
    val pastValues: List<ExternalPastMoveStatValue>,
    val statChanges: List<ExternalMoveStatChange>,
    val superContestEffect: APIResource,
    val target: NamedAPIResource,
    val type: NamedAPIResource,
)

@Serializable
data class ExternalContestComboSets(
    @SerialName("normal")
  val normalDetail: ExternalContestComboDetail,
    @SerialName("super")
  val superDetail: ExternalContestComboDetail,
)

@Serializable
data class ExternalContestComboDetail(
    val useBefore: List<NamedAPIResource>,
    val useAfter: List<NamedAPIResource>,
)

@Serializable
data class ExternalMoveFlavorText(
    val flavorText: String,
    val language: NamedAPIResource,
    val versionGroup: NamedAPIResource,
)

@Serializable
data class ExternalMoveMetaData(
    val ailment: NamedAPIResource,
    val category: NamedAPIResource,
    val minHits: Int,
    val maxHits: Int,
    val minTurns: Int,
    val maxTurns: Int,
    val drain: Int,
    val healing: Int,
    val critRate: Int,
    val ailmentChance: Int,
    val flinchChance: Int,
    val statChance: Int,
)

@Serializable
data class ExternalMoveStatChange(
    val change: Int,
    val stat: NamedAPIResource,
)

@Serializable
data class ExternalPastMoveStatValue(
    val accuracy: Int,
    val effectChance: Int,
    val power: Int,
    val pp: Int,
    val effectEntries: List<ExternalVerboseEffect>,
    val type: NamedAPIResource,
    val versionGroup: NamedAPIResource,
)
