package hu.danielwolf.pokecounter.api.counter.dto

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
