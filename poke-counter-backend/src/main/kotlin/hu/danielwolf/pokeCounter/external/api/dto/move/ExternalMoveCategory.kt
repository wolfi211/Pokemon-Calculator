package hu.danielwolf.pokeCounter.external.api.dto.move

import hu.danielwolf.pokeCounter.external.api.dto.ExternalDescription
import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalMoveCategory(
  val id: Int,
  val name: String,
  val moves: List<NamedAPIResource>,
  val descriptions: List<ExternalDescription>,
)