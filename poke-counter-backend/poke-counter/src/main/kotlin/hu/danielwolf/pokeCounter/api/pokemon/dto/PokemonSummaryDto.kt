package hu.danielwolf.pokeCounter.api.pokemon.dto

import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSummaryDto(
  val id: Int,
  val name: String,
  val sprite: String?,
  val cry: String?,
  val localizedName: String,
  val species: MinifiedSpeciesDto,
  val types: Set<MinifiedTypeDto>,
  val stats: StatBlockDto,
)

/** Alias kept for the search endpoint response type. */
typealias MinifiedPokemonSearchResponse = PokemonSummaryDto

fun Pokemon.toSummaryDto(): PokemonSummaryDto =
  PokemonSummaryDto(
    id = id,
    name = name,
    sprite = sprite,
    cry = cry,
    localizedName = resolveLocalizedDisplayName(),
    species = MinifiedSpeciesDto(name = species?.name ?: name),
    types = types.map { it.toMinifiedType() }.toSet(),
    stats = toStatBlockDto(),
  )

fun Pokemon.toMinifiedDto(): PokemonSummaryDto = toSummaryDto()

private fun Pokemon.resolveLocalizedDisplayName(): String {
  val defaultForm = forms.firstOrNull { it.isDefault == true }
  val fromForm =
    defaultForm?.names?.get("en")?.takeIf { it.isNotBlank() }
      ?: forms.firstOrNull()?.names?.get("en")?.takeIf { it.isNotBlank() }
  val fromSpecies = species?.names?.get("en")?.takeIf { it.isNotBlank() }
  return fromForm ?: fromSpecies ?: name
}
