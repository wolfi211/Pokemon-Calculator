@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalNature (
  val id: Int,
  val name: String,
  @param:JsonProperty("decreased_stat") val decreasedStat: NamedAPIResource?,
  @param:JsonProperty("increased_stat") val increasedStat: NamedAPIResource?,
  @param:JsonProperty("hates_flavor") val hatesFlavor: NamedAPIResource?,
  @param:JsonProperty("likes_flavor") val likesFlavor: NamedAPIResource?,
  @param:JsonProperty("pokeathlon_stat_changes") val pokeathlonStatChanges: List<ExternalNatureStatChange>,
  @param:JsonProperty("move_battle_style_preferences") val moveBattleStylePreferences: List<ExternalMoveBattleStylePreference>,
  val names: List<ExternalName>
)

data class ExternalNatureStatChange(
  @param:JsonProperty("max_change") val maxChange: Int,
  @param:JsonProperty("pokeathlon_stat") val pokeathlonStat: NamedAPIResource,
)

data class ExternalMoveBattleStylePreference(
  @param:JsonProperty("low_hp_preference") val lowHpPreference: Int,
  @param:JsonProperty("high_hp_preference") val highHpPreference: Int,
  @param:JsonProperty("move_battle_style") val moveBattleStyle: NamedAPIResource,
)