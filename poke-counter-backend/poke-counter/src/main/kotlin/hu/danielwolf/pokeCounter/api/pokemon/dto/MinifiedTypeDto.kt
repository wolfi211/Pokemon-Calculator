package hu.danielwolf.pokeCounter.api.pokemon.dto

import hu.danielwolf.pokeCounter.domain.model.pokemon.PokemonType
import kotlinx.serialization.Serializable

@Serializable
data class MinifiedTypeDto(
  val id: Int,
  val slot: Int?,
  val name: String,
  val localizedName: String,
)

fun PokemonType.toMinifiedType(): MinifiedTypeDto {
  return MinifiedTypeDto(
    id = this.type.id,
    slot = this.slot,
    name = this.type.name,
    localizedName = this.type.names?.get("en")!!
  )
}
