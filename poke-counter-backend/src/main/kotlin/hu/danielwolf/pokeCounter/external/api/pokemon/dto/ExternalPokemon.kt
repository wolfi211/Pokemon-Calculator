package hu.danielwolf.pokeCounter.external.api.pokemon.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalVersionGameIndex
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalPokemon(
  val id: Int,
  val name: String,
  @JsonProperty("base_experience") val baseExperience: Int?,
  val height: Int,
  @JsonProperty("is_default") val isDefault: Boolean,
  val order: Int,
  val weight: Int,
  val abilities: List<ExternalPokemonAbility>,
  val forms: List<NamedAPIResource>,
  @JsonProperty("game_indices") val gameIndices: List<ExternalVersionGameIndex>,
  @JsonProperty("held_items") val heldItems: List<ExternalPokemonHeldItem>,
  @JsonProperty("location_area_encounters") val locationAreaEncounters: String,
  val moves: List<ExternalPokemonMove>,
  @JsonProperty("past_types") val pastTypes: List<ExternalPokemonTypePast>,
  @JsonProperty("past_abilities") val pastAbilities: List<ExternalPokemonAbilityPast>,
  @JsonProperty("past_stats") val pastStats: List<ExternalPokemonStatPast>,
  val sprites: ExternalPokemonSprites,
  val cries: ExternalPokemonCries,
  val species: NamedAPIResource,
  val stats: List<ExternalPokemonStat>,
  val types: List<ExternalPokemonType>,
)

data class ExternalPokemonAbility(
  @JsonProperty("is_hidden") val isHidden: Boolean,
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
  @JsonProperty("version_details") val versionDetails: List<ExternalPokemonHeldItemVersion>,
)

data class ExternalPokemonHeldItemVersion(
  val version: NamedAPIResource,
  val rarity: Int,
)

data class ExternalPokemonMove(
  val move: NamedAPIResource,
  @JsonProperty("version_group_details") val versionGroupDetails: List<ExternalPokemonMoveVersion>,
)

data class ExternalPokemonMoveVersion(
  @JsonProperty("move_learn_method") val moveLearnMethod: NamedAPIResource,
  @JsonProperty("version_group") val versionGroup: NamedAPIResource,
  @JsonProperty("level_learned_at") val levelLearnedAt: Int,
  val order: Int?,
)

data class ExternalPokemonStat(
  val stat: NamedAPIResource,
  val effort: Int,
  @JsonProperty("base_stat") val baseStat: Int,
)

data class ExternalPokemonSprites(
  @JsonProperty("front_default") val frontDefault: String?,
  @JsonProperty("front_shiny") val frontShiny: String?,
  @JsonProperty("front_female") val frontFemale: String?,
  @JsonProperty("front_shiny_female") val frontShinyFemale: String?,
  @JsonProperty("back_default") val backDefault: String?,
  @JsonProperty("back_shiny") val backShiny: String?,
  @JsonProperty("back_female") val backFemale: String?,
  @JsonProperty("back_shiny_female") val backShinyFemale: String?,
  val other: Map<String, Map<String, String>>
)

data class ExternalPokemonCries(
  val latest: String?,
  val legacy: String?,
)