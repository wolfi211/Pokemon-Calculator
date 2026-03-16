package hu.danielwolf.pokeCounter.external.api.berries.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource

data class ExternalBerryFirmness(
    val id: Int,
    val name: String,
    val berries: List<NamedAPIResource>,
    val names: List<ExternalName>,
)