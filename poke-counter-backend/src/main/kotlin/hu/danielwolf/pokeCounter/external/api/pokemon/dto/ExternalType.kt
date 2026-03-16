package hu.danielwolf.pokeCounter.external.api.pokemon.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalGenerationGameIndex
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalType (
    val id: Int,
    val name: String,
    @JsonProperty("damage_relations") val damageRelations: ExternalTypeRelations,
    @JsonProperty("past_damage_relations") val pastDamageRelations: List<ExternalTypeRelationsPast>,
    @JsonProperty("game_indices") val gameIndices: List<ExternalGenerationGameIndex>,
    val generation: NamedAPIResource,
    @JsonProperty("move_damage_class") val moveDamageClass: NamedAPIResource?,
    val names: List<ExternalName>,
    val pokemon: List<ExternalTypePokemon>,
    val moves: List<NamedAPIResource>,
)

data class ExternalTypePokemon(
    val slot: Int,
    val pokemon: NamedAPIResource,
)

data class ExternalTypeRelations(
    @JsonProperty("no_damage_to") val noDamageTo: List<NamedAPIResource>,
    @JsonProperty("half_damage_to") val halfDamageTo: List<NamedAPIResource>,
    @JsonProperty("double_damage_to") val doubleDamageTo: List<NamedAPIResource>,
    @JsonProperty("no_damage_from") val noDamageFrom: List<NamedAPIResource>,
    @JsonProperty("half_damage_from") val halfDamageFrom: List<NamedAPIResource>,
    @JsonProperty("double_damage_from") val doubleDamageFrom: List<NamedAPIResource>,
)

data class ExternalTypeRelationsPast(
    val generation: NamedAPIResource,
    @JsonProperty("damage_relations") val damageRelations: ExternalTypeRelations,
)