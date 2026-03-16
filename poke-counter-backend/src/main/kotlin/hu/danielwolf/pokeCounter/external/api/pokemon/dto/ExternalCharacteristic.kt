package hu.danielwolf.pokeCounter.external.api.pokemon.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalDescription
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource

data class ExternalCharacteristic(
    val id: Int,
    val geneModulo: Int,
    val possibleValues: List<Int>,
    val highestStat: NamedAPIResource,
    val descriptions: List<ExternalDescription>
)