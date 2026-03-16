package hu.danielwolf.pokeCounter.external.api.pokemon.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource

data class ExternalPokemonShape (
    val id: Int,
    val name: String,
    val awesomeNames: List<ExternalAwesomeName>,
    val names: List<ExternalName>,
    val pokemonSpecies: List<NamedAPIResource>,
)

data class ExternalAwesomeName(
    val awesomeName: String,
    val language: NamedAPIResource,
)