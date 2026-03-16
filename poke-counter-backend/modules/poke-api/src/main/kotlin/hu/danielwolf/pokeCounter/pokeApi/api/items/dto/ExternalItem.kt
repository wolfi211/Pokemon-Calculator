@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.items.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.APIResource
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalGenerationGameIndex
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalMachineVersionDetail
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalVerboseEffect
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalVersionGroupFlavorText
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalItem(
  val id: Int,
  val name: String,
  val cost: Int,
  @param:JsonProperty("fling_power") val flingPower: Int,
  @param:JsonProperty("fling_effect") val flingEffect: NamedAPIResource,
  val attributes: List<NamedAPIResource>,
  val category: NamedAPIResource,
  @param:JsonProperty("effect_entries") val effectEntries: List<ExternalVerboseEffect>,
  @param:JsonProperty("flavor_text_entries") val flavorTextEntries: List<ExternalVersionGroupFlavorText>,
  @param:JsonProperty("game_indices") val gameIndices: List<ExternalGenerationGameIndex>,
  val names: List<ExternalName>,
  val sprites: ExternalItemSprites,
  @param:JsonProperty("held_by_pokemon") val heldByPokemon: List<ExternalItemHolderPokemon>,
  @param:JsonProperty("baby_trigger_for") val babyTriggerFor: APIResource?,
  val machines: List<ExternalMachineVersionDetail>
)

data class ExternalItemSprites(
  val default: String,
)

data class ExternalItemHolderPokemon(
  val pokemon: NamedAPIResource,
  @param:JsonProperty("version_details") val versionDetails: List<ExternalItemHolderPokemonVersionDetail>
)

data class ExternalItemHolderPokemonVersionDetail(
  val rarity: Int,
  val version: NamedAPIResource,
)