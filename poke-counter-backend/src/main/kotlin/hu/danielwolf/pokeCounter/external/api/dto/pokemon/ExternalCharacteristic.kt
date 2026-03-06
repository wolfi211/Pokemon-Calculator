package hu.danielwolf.pokeCounter.external.api.dto.pokemon

import hu.danielwolf.pokeCounter.external.api.dto.ExternalDescription
import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalCharacteristic(
  val id: Int,
  val geneModulo: Int,
  val possibleValues: List<Int>,
  val highestStat: NamedAPIResource,
  val descriptions: List<ExternalDescription>
)