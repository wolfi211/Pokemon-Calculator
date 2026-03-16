@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalGenerationGameIndex
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalType (
  val id: Int,
  val name: String,
  @param:JsonProperty("damage_relations") val damageRelations: ExternalTypeRelations,
  @param:JsonProperty("past_damage_relations") val pastDamageRelations: List<ExternalTypeRelationsPast>,
  @param:JsonProperty("game_indices") val gameIndices: List<ExternalGenerationGameIndex>,
  val generation: NamedAPIResource,
  @param:JsonProperty("move_damage_class") val moveDamageClass: NamedAPIResource?,
  val names: List<ExternalName>,
  val pokemon: List<ExternalTypePokemon>,
  val moves: List<NamedAPIResource>,
)

data class ExternalTypePokemon(
  val slot: Int,
  val pokemon: NamedAPIResource,
)

data class ExternalTypeRelations(
  @param:JsonProperty("no_damage_to") val noDamageTo: List<NamedAPIResource>,
  @param:JsonProperty("half_damage_to") val halfDamageTo: List<NamedAPIResource>,
  @param:JsonProperty("double_damage_to") val doubleDamageTo: List<NamedAPIResource>,
  @param:JsonProperty("no_damage_from") val noDamageFrom: List<NamedAPIResource>,
  @param:JsonProperty("half_damage_from") val halfDamageFrom: List<NamedAPIResource>,
  @param:JsonProperty("double_damage_from") val doubleDamageFrom: List<NamedAPIResource>,
)

data class ExternalTypeRelationsPast(
  val generation: NamedAPIResource,
  @param:JsonProperty("damage_relations") val damageRelations: ExternalTypeRelations,
)