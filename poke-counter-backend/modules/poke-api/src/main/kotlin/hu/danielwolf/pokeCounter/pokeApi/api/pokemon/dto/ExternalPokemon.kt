@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalVersionGameIndex
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalPokemon(
  val id: Int,
  val name: String,
  @param:JsonProperty("base_experience") val baseExperience: Int?,
  val height: Int,
  @param:JsonProperty("is_default") val isDefault: Boolean,
  val order: Int,
  val weight: Int,
  val abilities: List<ExternalPokemonAbility>,
  val forms: List<NamedAPIResource>,
  @param:JsonProperty("game_indices") val gameIndices: List<ExternalVersionGameIndex>,
  @param:JsonProperty("held_items") val heldItems: List<ExternalPokemonHeldItem>,
  @param:JsonProperty("location_area_encounters") val locationAreaEncounters: String,
  val moves: List<ExternalPokemonMove>,
  @param:JsonProperty("past_types") val pastTypes: List<ExternalPokemonTypePast>,
  @param:JsonProperty("past_abilities") val pastAbilities: List<ExternalPokemonAbilityPast>,
  @param:JsonProperty("past_stats") val pastStats: List<ExternalPokemonStatPast>,
  val sprites: ExternalPokemonSprites,
  val cries: ExternalPokemonCries,
  val species: NamedAPIResource,
  val stats: List<ExternalPokemonStat>,
  val types: List<ExternalPokemonType>,
)

data class ExternalPokemonAbility(
  @param:JsonProperty("is_hidden") val isHidden: Boolean,
  val slot: Int,
  val ability: NamedAPIResource?,
)

data class ExternalPokemonType(
  val slot: Int,
  val type: NamedAPIResource,
)

data class ExternalPokemonFormType(
  val slot: Int,
  val type: NamedAPIResource,
)

data class ExternalPokemonTypePast(
  val generation: NamedAPIResource,
  val types: List<ExternalPokemonType>,
)

data class ExternalPokemonAbilityPast(
  val generation: NamedAPIResource,
  val abilities: List<ExternalPokemonAbility>,
)

data class ExternalPokemonStatPast(
  val generation: NamedAPIResource,
  val stats: List<ExternalPokemonStat>,
)

data class ExternalPokemonHeldItem(
  val item: NamedAPIResource,
  @param:JsonProperty("version_details") val versionDetails: List<ExternalPokemonHeldItemVersion>,
)

data class ExternalPokemonHeldItemVersion(
  val version: NamedAPIResource,
  val rarity: Int,
)

data class ExternalPokemonMove(
  val move: NamedAPIResource,
  @param:JsonProperty("version_group_details") val versionGroupDetails: List<ExternalPokemonMoveVersion>,
)

data class ExternalPokemonMoveVersion(
  @param:JsonProperty("move_learn_method") val moveLearnMethod: NamedAPIResource,
  @param:JsonProperty("version_group") val versionGroup: NamedAPIResource,
  @param:JsonProperty("level_learned_at") val levelLearnedAt: Int,
  val order: Int?,
)

data class ExternalPokemonStat(
  val stat: NamedAPIResource,
  val effort: Int,
  @param:JsonProperty("base_stat") val baseStat: Int,
)

data class ExternalPokemonSprites(
  @param:JsonProperty("front_default") val frontDefault: String?,
  @param:JsonProperty("front_shiny") val frontShiny: String?,
  @param:JsonProperty("front_female") val frontFemale: String?,
  @param:JsonProperty("front_shiny_female") val frontShinyFemale: String?,
  @param:JsonProperty("back_default") val backDefault: String?,
  @param:JsonProperty("back_shiny") val backShiny: String?,
  @param:JsonProperty("back_female") val backFemale: String?,
  @param:JsonProperty("back_shiny_female") val backShinyFemale: String?,
  val other: Map<String, Map<String, String>>
)

data class ExternalPokemonCries(
  val latest: String?,
  val legacy: String?,
)