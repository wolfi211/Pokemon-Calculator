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
    localizedName = resolveLocalizedDisplayName(),
    types = this.types.map { it.toMinifiedType() }.toSet(),
  )
}

private fun Pokemon.resolveLocalizedDisplayName(): String {
  val defaultForm = forms.firstOrNull { it.isDefault == true }
  val fromForm =
    defaultForm?.names?.get("en")?.takeIf { it.isNotBlank() }
      ?: forms.firstOrNull()?.names?.get("en")?.takeIf { it.isNotBlank() }
  val fromSpecies = species?.names?.get("en")?.takeIf { it.isNotBlank() }
  return fromForm ?: fromSpecies ?: name
}
