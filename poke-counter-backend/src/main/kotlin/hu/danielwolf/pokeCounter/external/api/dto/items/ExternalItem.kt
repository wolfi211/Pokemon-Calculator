package hu.danielwolf.pokeCounter.external.api.dto.items

import hu.danielwolf.pokeCounter.external.api.dto.APIResource
import hu.danielwolf.pokeCounter.external.api.dto.ExternalGenerationGameIndex
import hu.danielwolf.pokeCounter.external.api.dto.ExternalMachineVersionDetail
import hu.danielwolf.pokeCounter.external.api.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.dto.ExternalVerboseEffect
import hu.danielwolf.pokeCounter.external.api.dto.ExternalVersionGroupFlavorText
import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalItem(
  val id: Int,
  val name: String,
  val cost: Int,
  val flingPower: Int,
  val flingEffect: NamedAPIResource,
  val attributes: List<NamedAPIResource>,
  val category: NamedAPIResource,
  val effectEntries: List<ExternalVerboseEffect>,
  val flavorTextEntries: List<ExternalVersionGroupFlavorText>,
  val gameIndices: List<ExternalGenerationGameIndex>,
  val names: List<ExternalName>,
  val sprites: ExternalItemSprites,
  val heldByPokemon: List<ExternalItemHolderPokemon>,
  val babyTriggerFor: APIResource,
  val machines: List<ExternalMachineVersionDetail>
)

@Serializable
data class ExternalItemSprites(
  val default: String,
)

@Serializable
data class ExternalItemHolderPokemon(
  val pokemon: NamedAPIResource,
  val versionDetails: List<ExternalItemHolderPokemonVersionDetail>
)

@Serializable
data class ExternalItemHolderPokemonVersionDetail(
  val rarity: Int,
  val version: NamedAPIResource,
)