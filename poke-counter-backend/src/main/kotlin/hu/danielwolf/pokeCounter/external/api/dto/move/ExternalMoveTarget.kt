package hu.danielwolf.pokeCounter.external.api.dto.move

import hu.danielwolf.pokeCounter.external.api.dto.ExternalDescription
import hu.danielwolf.pokeCounter.external.api.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalMoveTarget(
  val id: Int,
  val name: String,
  val descriptions: List<ExternalDescription>,
  val moves: List<NamedAPIResource>,
  val names: List<ExternalName>,
)
