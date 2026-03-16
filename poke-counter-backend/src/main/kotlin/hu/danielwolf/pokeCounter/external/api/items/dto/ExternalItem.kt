package hu.danielwolf.pokeCounter.external.api.items.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.APIResource
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalGenerationGameIndex
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalMachineVersionDetail
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalVerboseEffect
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalVersionGroupFlavorText
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalItem(
    val id: Int,
    val name: String,
    val cost: Int,
    @JsonProperty("fling_power") val flingPower: Int,
    @JsonProperty("fling_effect") val flingEffect: NamedAPIResource,
    val attributes: List<NamedAPIResource>,
    val category: NamedAPIResource,
    @JsonProperty("effect_entries") val effectEntries: List<ExternalVerboseEffect>,
    @JsonProperty("flavor_text_entries") val flavorTextEntries: List<ExternalVersionGroupFlavorText>,
    @JsonProperty("game_indices") val gameIndices: List<ExternalGenerationGameIndex>,
    val names: List<ExternalName>,
    val sprites: ExternalItemSprites,
    @JsonProperty("held_by_pokemon") val heldByPokemon: List<ExternalItemHolderPokemon>,
    @JsonProperty("baby_trigger_for") val babyTriggerFor: APIResource?,
    val machines: List<ExternalMachineVersionDetail>
)

data class ExternalItemSprites(
  val default: String,
)

data class ExternalItemHolderPokemon(
    val pokemon: NamedAPIResource,
    @JsonProperty("version_details") val versionDetails: List<ExternalItemHolderPokemonVersionDetail>
)

data class ExternalItemHolderPokemonVersionDetail(
    val rarity: Int,
    val version: NamedAPIResource,
)