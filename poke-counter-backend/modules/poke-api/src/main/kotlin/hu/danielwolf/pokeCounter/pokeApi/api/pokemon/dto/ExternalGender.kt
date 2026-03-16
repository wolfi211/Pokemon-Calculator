@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalGender(
  val id: Int,
  val name: String,
  @param:JsonProperty("pokemon_species_details") val pokemonSpeciesDetails: List<ExternalPokemonSpeciesGender>,
  @param:JsonProperty("required_for_evolution") val requiredForEvolution: List<NamedAPIResource>
)

data class ExternalPokemonSpeciesGender(
  val rate: Int,
  @param:JsonProperty("pokemon_species") val pokemonSpecies: NamedAPIResource
)