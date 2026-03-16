@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalEffect
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalVerboseEffect
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalAbility(
  val id: Int,
  val name: String,
  @param:JsonProperty("is_main_series") val isMainSeries: Boolean,
  val generation: NamedAPIResource,
  val names: List<ExternalName>,
  @param:JsonProperty("effect_entries") val effectEntries: List<ExternalVerboseEffect>,
  @param:JsonProperty("effect_changes") val effectChanges: List<ExternalAbilityEffectChange>,
  @param:JsonProperty("flavor_text_entries") val flavorTextEntries: List<ExternalAbilityFlavorText>,
  val pokemon: List<ExternalAbilityPokemon>,
)

data class ExternalAbilityEffectChange(
  @param:JsonProperty("effect_entries") val effectEntries: List<ExternalEffect>,
  @param:JsonProperty("version_group") val versionGroup: NamedAPIResource,
)

data class ExternalAbilityFlavorText(
  @param:JsonProperty("flavor_text") val flavorText: String,
  val language: NamedAPIResource,
  @param:JsonProperty("version_group") val versionGroup: NamedAPIResource,
)

data class ExternalAbilityPokemon(
  @param:JsonProperty("is_hidden") val isHidden: Boolean,
  val slot: Int,
  val pokemon: NamedAPIResource,
)