package hu.danielwolf.pokeCounter.external.api.dto.pokemon

import hu.danielwolf.pokeCounter.external.api.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalPokemonForm (
  val id: Int,
  val name: String,
  val order: Int,
  val formOrder: Int,
  val isDefault: Boolean,
  val isBattleOnly: Boolean,
  val isMega: Boolean,
  val formName: String,
  val pokemon: NamedAPIResource,
  val types: List<ExternalPokemonFormType>,
  val sprites: ExternalPokemonFormSprites,
  val versionGroup: NamedAPIResource,
  val names: List<ExternalName>,
  val formNames: List<ExternalName>,
)

@Serializable
data class ExternalPokemonFormSprites(
  val frontDefault: String,
  val frontShiny: String,
  val backDefault: String,
  val backShiny: String,
)