package hu.danielwolf.pokeCounter.external.api.move.dto

import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalAbilityEffectChange
import hu.danielwolf.pokeCounter.external.api.utilities.dto.APIResource
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalMachineVersionDetail
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalVerboseEffect
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalMove(
    val id: Int,
    val name: String,
    val accuracy: Int?,
    @JsonProperty("effect_chance") val effectChance: Int?,
    val pp: Int?,
    val priority: Int,
    val power: Int?,
    @JsonProperty("contest_combos") val contestCombos: ExternalContestComboSets?,
    @JsonProperty("contest_type") val contestType: NamedAPIResource?,
    @JsonProperty("contest_effect") val contestEffect: APIResource?,
    @JsonProperty("damage_class") val damageClass: NamedAPIResource,
    @JsonProperty("effect_entries") val effectEntries: List<ExternalVerboseEffect>,
    @JsonProperty("effect_changes") val effectChanges: List<ExternalAbilityEffectChange>,
    @JsonProperty("learned_by_pokemon") val learnedByPokemon: List<NamedAPIResource>,
    @JsonProperty("flavor_text_entries") val flavorTextEntries: List<ExternalMoveFlavorText>,
    val generation: NamedAPIResource,
    val machines: List<ExternalMachineVersionDetail>,
    val meta: ExternalMoveMetaData?,
    val names: List<ExternalName>,
    @JsonProperty("past_values") val pastValues: List<ExternalPastMoveStatValue>?,
    @JsonProperty("stat_changes") val statChanges: List<ExternalMoveStatChange>,
    @JsonProperty("super_contest_effect") val superContestEffect: APIResource?,
    val target: NamedAPIResource,
    val type: NamedAPIResource,
)

data class ExternalContestComboSets(
    @JsonProperty("normal")
  val normalDetail: ExternalContestComboDetail,
    @JsonProperty("super")
  val superDetail: ExternalContestComboDetail,
)

data class ExternalContestComboDetail(
    @JsonProperty("use_before") val useBefore: List<NamedAPIResource>?,
    @JsonProperty("use_after") val useAfter: List<NamedAPIResource>?,
)

data class ExternalMoveFlavorText(
    @JsonProperty("flavor_text") val flavorText: String,
    val language: NamedAPIResource,
    @JsonProperty("version_group") val versionGroup: NamedAPIResource,
)

data class ExternalMoveMetaData(
    val ailment: NamedAPIResource,
    val category: NamedAPIResource,
    @JsonProperty("min_hits") val minHits: Int?,
    @JsonProperty("max_hits") val maxHits: Int?,
    @JsonProperty("min_turns") val minTurns: Int?,
    @JsonProperty("max_turns") val maxTurns: Int?,
    val drain: Int,
    val healing: Int,
    @JsonProperty("crit_rate") val critRate: Int,
    @JsonProperty("ailment_chance") val ailmentChance: Int,
    @JsonProperty("flinch_chance") val flinchChance: Int,
    @JsonProperty("stat_chance") val statChance: Int,
)

data class ExternalMoveStatChange(
    val change: Int,
    val stat: NamedAPIResource,
)

data class ExternalPastMoveStatValue(
    val accuracy: Int?,
    @JsonProperty("effect_chance") val effectChance: Int?,
    val power: Int?,
    val pp: Int?,
    @JsonProperty("effect_entries") val effectEntries: List<ExternalVerboseEffect>?,
    val type: NamedAPIResource?,
    @JsonProperty("version_group") val versionGroup: NamedAPIResource,
)