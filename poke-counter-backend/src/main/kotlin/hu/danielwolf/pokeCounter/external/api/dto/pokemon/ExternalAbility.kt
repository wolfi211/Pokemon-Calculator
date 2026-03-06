package hu.danielwolf.pokeCounter.external.api.dto.pokemon

import hu.danielwolf.pokeCounter.external.api.dto.ExternalEffect
import hu.danielwolf.pokeCounter.external.api.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.dto.ExternalVerboseEffect
import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalAbility(
  val id: Int,
  val name: String,
  val isMainSeries: Boolean,
  val generation: NamedAPIResource,
  val names: List<ExternalName>,
  val effectEntries: List<ExternalVerboseEffect>,
  val effectChanges: List<ExternalAbilityEffectChange>,
  val flavorTextEntries: List<ExternalAbilityFlavorText>,
  val pokemon: List<ExternalAbilityPokemon>,
)

@Serializable
data class ExternalAbilityEffectChange(
  val effectEntries: List<ExternalEffect>,
  val versionGroup: NamedAPIResource,
)

@Serializable
data class ExternalAbilityFlavorText(
  val flavorText: String,
  val language: NamedAPIResource,
  val versionGroup: NamedAPIResource,
)

@Serializable
data class ExternalAbilityPokemon(
  val isHidden: Boolean,
  val slot: Int,
  val pokemon: NamedAPIResource,
)