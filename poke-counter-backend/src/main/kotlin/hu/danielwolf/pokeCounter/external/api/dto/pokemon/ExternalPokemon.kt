package hu.danielwolf.pokeCounter.external.api.dto.pokemon

import hu.danielwolf.pokeCounter.external.api.dto.ExternalVersionGameIndex
import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalPokemon(
  val id: Int,
  val name: String,
  val baseExperience: Int,
  val height: Int,
  val isDefault: Boolean,
  val order: Int,
  val weight: Int,
  val abilities: List<ExternalPokemonAbility>,
  val forms: List<NamedAPIResource>,
  val gameIndices: List<ExternalVersionGameIndex>,
  val heldItems: List<ExternalPokemonHeldItem>,
  val locationAreaEncounters: String,
  val moves: List<ExternalPokemonMove>,
  val pastTypes: List<ExternalPokemonTypePast>,
  val pastAbilities: List<ExternalPokemonAbilityPast>,
  val pastStats: List<ExternalPokemonStatPast>,
  val sprites: ExternalPokemonSprites,
  val cries: ExternalPokemonCries,
  val species: NamedAPIResource,
  val stats: List<ExternalPokemonStat>,
  val types: List<ExternalPokemonType>,
)

@Serializable
data class ExternalPokemonAbility(
  val isHidden: Boolean,
  val slot: Int,
  val ability: NamedAPIResource,
)

@Serializable
data class ExternalPokemonType(
  val slot: Int,
  val type: NamedAPIResource,
)

@Serializable
data class ExternalPokemonFormType(
  val slot: Int,
  val type: NamedAPIResource,
)

@Serializable
data class ExternalPokemonTypePast(
  val generation: NamedAPIResource,
  val types: List<ExternalPokemonType>,
)

@Serializable
data class ExternalPokemonAbilityPast(
  val generation: NamedAPIResource,
  val abilities: List<ExternalPokemonAbility>,
)

@Serializable
data class ExternalPokemonStatPast(
  val generation: NamedAPIResource,
  val stats: List<ExternalPokemonStat>,
)

@Serializable
data class ExternalPokemonHeldItem(
  val item: NamedAPIResource,
  val versionDetails: List<ExternalPokemonHeldItemVersion>,
)

@Serializable
data class ExternalPokemonHeldItemVersion(
  val version: NamedAPIResource,
  val rarity: Int,
)

@Serializable
data class ExternalPokemonMove(
  val move: NamedAPIResource,
  val versionGroupDetails: List<ExternalPokemonMoveVersion>,
)

@Serializable
data class ExternalPokemonMoveVersion(
  val moveLearnMethod: NamedAPIResource,
  val versionGroup: NamedAPIResource,
  val levelLearnedAt: Int,
  val order: Int,
)

@Serializable
data class ExternalPokemonStat(
  val stat: NamedAPIResource,
  val effort: Int,
  val baseStat: Int,
)

@Serializable
data class ExternalPokemonSprites(
  val frontDefault: String,
  val frontShiny: String,
  val frontFemale: String,
  val frontShinyFemale: String,
  val backDefault: String,
  val backShiny: String,
  val backFemale: String,
  val backShinyFemale: String,
)

@Serializable
data class ExternalPokemonCries(
  val latest: String,
  val legacy: String,
)
