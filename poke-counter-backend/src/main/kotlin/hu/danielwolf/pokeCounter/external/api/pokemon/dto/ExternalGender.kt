package hu.danielwolf.pokeCounter.external.api.pokemon.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalGender(
    val id: Int,
    val name: String,
    @JsonProperty("pokemon_species_details") val pokemonSpeciesDetails: List<ExternalPokemonSpeciesGender>,
    @JsonProperty("required_for_evolution") val requiredForEvolution: List<NamedAPIResource>
)

data class ExternalPokemonSpeciesGender(
  val rate: Int,
  @JsonProperty("pokemon_species") val pokemonSpecies: NamedAPIResource
)