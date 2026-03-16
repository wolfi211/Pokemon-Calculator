@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalPokemonForm (
  val id: Int,
  val name: String,
  val order: Int,
  @param:JsonProperty("form_order") val formOrder: Int,
  @param:JsonProperty("is_default") val isDefault: Boolean,
  @param:JsonProperty("is_battle_only") val isBattleOnly: Boolean,
  @param:JsonProperty("is_mega") val isMega: Boolean,
  @param:JsonProperty("form_name") val formName: String,
  val pokemon: NamedAPIResource,
  val types: List<ExternalPokemonFormType>,
  val sprites: ExternalPokemonFormSprites,
  @param:JsonProperty("version_group") val versionGroup: NamedAPIResource,
  val names: List<ExternalName>,
  @param:JsonProperty("form_names") val formNames: List<ExternalName>,
)

data class ExternalPokemonFormSprites(
  @param:JsonProperty("front_default") val frontDefault: String?,
  @param:JsonProperty("front_shiny") val frontShiny: String?,
  @param:JsonProperty("back_default") val backDefault: String?,
  @param:JsonProperty("back_shiny") val backShiny: String?,
)