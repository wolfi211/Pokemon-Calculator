package hu.danielwolf.pokeCounter.api.pokemon.dto

import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon
import kotlinx.serialization.Serializable

@Serializable
data class MinifiedPokemonSearchResponse(
  val id: Int,
  val name: String,
  val sprite: String?,
  val cry: String?,
  val types: Set<MinifiedTypeDto>,
  val localizedName: String,
)

fun Pokemon.toMinifiedDto(): MinifiedPokemonSearchResponse {
  return MinifiedPokemonSearchResponse(
    id = this.id,
    name = this.name,
    sprite = this.sprite,
    cry = this.cry,
    localizedName = this.forms.first().names?.get("en").takeIf { this.forms.first().names?.isNotEmpty() ?: false } ?: this.species?.names?.get("en")!!,
    types = this.types.map { it.toMinifiedType() }.toSet(),
  )
}
