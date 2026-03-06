package hu.danielwolf.pokeCounter.external.api.evolution.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalEvolutionTrigger(
    val id: Int,
    val name: String,
    val names: List<ExternalName>,
    val pokemonSpecies: List<NamedAPIResource>
)
