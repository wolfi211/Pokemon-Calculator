package hu.danielwolf.pokeCounter.external.api.dto.pokemon

import hu.danielwolf.pokeCounter.external.api.dto.ExternalGenerationGameIndex
import hu.danielwolf.pokeCounter.external.api.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalType (
  val id: Int,
  val name: String,
  val damageRelations: ExternalTypeRelations,
  val pastDamageRelations: List<ExternalTypeRelationsPast>,
  val gameIndeces: List<ExternalGenerationGameIndex>,
  val generation: NamedAPIResource,
  val moveDamageClass: NamedAPIResource,
  val names: List<ExternalName>,
  val pokemon: List<ExternalTypePokemon>,
  val moves: List<NamedAPIResource>,
)

@Serializable
data class ExternalTypePokemon(
  val slot: Int,
  val pokemon: NamedAPIResource,
)

@Serializable
data class ExternalTypeRelations(
  val noDamageTo: List<NamedAPIResource>,
  val halfDamageTo: List<NamedAPIResource>,
  val doubleDamageTo: List<NamedAPIResource>,
  val noDamageFrom: List<NamedAPIResource>,
  val halfDamageFrom: List<NamedAPIResource>,
  val doubleDamageFrom: List<NamedAPIResource>,
)

@Serializable
data class ExternalTypeRelationsPast(
  val generation: NamedAPIResource,
  val damageRelation: ExternalTypeRelations,
)