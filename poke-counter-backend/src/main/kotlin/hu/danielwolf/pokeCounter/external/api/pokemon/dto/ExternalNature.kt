package hu.danielwolf.pokeCounter.external.api.pokemon.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalNature (
    val id: Int,
    val name: String,
    @JsonProperty("decreased_stat") val decreasedStat: NamedAPIResource?,
    @JsonProperty("increased_stat") val increasedStat: NamedAPIResource?,
    @JsonProperty("hates_flavor") val hatesFlavor: NamedAPIResource?,
    @JsonProperty("likes_flavor") val likesFlavor: NamedAPIResource?,
    @JsonProperty("pokeathlon_stat_changes") val pokeathlonStatChanges: List<ExternalNatureStatChange>,
    @JsonProperty("move_battle_style_preferences") val moveBattleStylePreferences: List<ExternalMoveBattleStylePreference>,
    val names: List<ExternalName>
)

data class ExternalNatureStatChange(
    @JsonProperty("max_change") val maxChange: Int,
    @JsonProperty("pokeathlon_stat") val pokeathlonStat: NamedAPIResource,
)

data class ExternalMoveBattleStylePreference(
    @JsonProperty("low_hp_preference") val lowHpPreference: Int,
    @JsonProperty("high_hp_preference") val highHpPreference: Int,
    @JsonProperty("move_battle_style") val moveBattleStyle: NamedAPIResource,
)