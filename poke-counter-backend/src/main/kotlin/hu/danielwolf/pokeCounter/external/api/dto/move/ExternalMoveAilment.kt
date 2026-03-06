package hu.danielwolf.pokeCounter.external.api.dto.move

import hu.danielwolf.pokeCounter.external.api.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalMoveAilment(
  val id: Int,
  val name: String,
  val moves: List<NamedAPIResource>,
  val names: List<ExternalName>,
)