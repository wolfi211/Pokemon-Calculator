package hu.danielwolf.pokeCounter.external.api.dto.pokemon

import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalGender(
  val id: Int,
  val name: String,
  val pokemonSpeciesDetails: List<ExternalPokemonSpeciesGender>,
  val requiredForEvolution: List<NamedAPIResource>
)

@Serializable
data class ExternalPokemonSpeciesGender(
  val rate: Int,
  val pokemonSpecies: NamedAPIResource
)