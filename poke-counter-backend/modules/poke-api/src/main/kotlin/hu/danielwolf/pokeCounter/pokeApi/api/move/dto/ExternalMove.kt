@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.move.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto.ExternalAbilityEffectChange
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.APIResource
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalMachineVersionDetail
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalVerboseEffect
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalMove(
  val id: Int,
  val name: String,
  val accuracy: Int?,
  @param:JsonProperty("effect_chance") val effectChance: Int?,
  val pp: Int?,
  val priority: Int,
  val power: Int?,
  @param:JsonProperty("contest_combos") val contestCombos: ExternalContestComboSets?,
  @param:JsonProperty("contest_type") val contestType: NamedAPIResource?,
  @param:JsonProperty("contest_effect") val contestEffect: APIResource?,
  @param:JsonProperty("damage_class") val damageClass: NamedAPIResource,
  @param:JsonProperty("effect_entries") val effectEntries: List<ExternalVerboseEffect>,
  @param:JsonProperty("effect_changes") val effectChanges: List<ExternalAbilityEffectChange>,
  @param:JsonProperty("learned_by_pokemon") val learnedByPokemon: List<NamedAPIResource>,
  @param:JsonProperty("flavor_text_entries") val flavorTextEntries: List<ExternalMoveFlavorText>,
  val generation: NamedAPIResource,
  val machines: List<ExternalMachineVersionDetail>,
  val meta: ExternalMoveMetaData?,
  val names: List<ExternalName>,
  @param:JsonProperty("past_values") val pastValues: List<ExternalPastMoveStatValue>?,
  @param:JsonProperty("stat_changes") val statChanges: List<ExternalMoveStatChange>,
  @param:JsonProperty("super_contest_effect") val superContestEffect: APIResource?,
  val target: NamedAPIResource,
  val type: NamedAPIResource,
)

data class ExternalContestComboSets(
  @param:JsonProperty("normal")
  val normalDetail: ExternalContestComboDetail,
  @param:JsonProperty("super")
  val superDetail: ExternalContestComboDetail,
)

data class ExternalContestComboDetail(
  @param:JsonProperty("use_before") val useBefore: List<NamedAPIResource>?,
  @param:JsonProperty("use_after") val useAfter: List<NamedAPIResource>?,
)

data class ExternalMoveFlavorText(
  @param:JsonProperty("flavor_text") val flavorText: String,
  val language: NamedAPIResource,
  @param:JsonProperty("version_group") val versionGroup: NamedAPIResource,
)

data class ExternalMoveMetaData(
  val ailment: NamedAPIResource,
  val category: NamedAPIResource,
  @param:JsonProperty("min_hits") val minHits: Int?,
  @param:JsonProperty("max_hits") val maxHits: Int?,
  @param:JsonProperty("min_turns") val minTurns: Int?,
  @param:JsonProperty("max_turns") val maxTurns: Int?,
  val drain: Int,
  val healing: Int,
  @param:JsonProperty("crit_rate") val critRate: Int,
  @param:JsonProperty("ailment_chance") val ailmentChance: Int,
  @param:JsonProperty("flinch_chance") val flinchChance: Int,
  @param:JsonProperty("stat_chance") val statChance: Int,
)

data class ExternalMoveStatChange(
  val change: Int,
  val stat: NamedAPIResource,
)

data class ExternalPastMoveStatValue(
  val accuracy: Int?,
  @param:JsonProperty("effect_chance") val effectChance: Int?,
  val power: Int?,
  val pp: Int?,
  @param:JsonProperty("effect_entries") val effectEntries: List<ExternalVerboseEffect>?,
  val type: NamedAPIResource?,
  @param:JsonProperty("version_group") val versionGroup: NamedAPIResource,
)