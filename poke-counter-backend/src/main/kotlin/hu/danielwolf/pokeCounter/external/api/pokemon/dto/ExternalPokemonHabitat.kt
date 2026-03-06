package hu.danielwolf.pokeCounter.external.api.pokemon.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalPokemonHabitat(
    val id: Int,
    val name: String,
    val names: List<ExternalName>,
    val pokemonSpecies: List<NamedAPIResource>
)
