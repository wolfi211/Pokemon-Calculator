package hu.danielwolf.pokeCounter.external.api.dto.pokemon

import hu.danielwolf.pokeCounter.external.api.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalPokemonColor(
  val id: Int,
  val name: String,
  val names: List<ExternalName>,
  val pokemonSpecies: List<NamedAPIResource>
)