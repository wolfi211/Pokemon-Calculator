package hu.danielwolf.pokeCounter.external.api.dto.machines

import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalMachine(
  val id: Int,
  val item: NamedAPIResource,
  val move: NamedAPIResource,
  val versionGroup: NamedAPIResource,
)
