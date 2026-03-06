package hu.danielwolf.pokeCounter.external.api.dto.move

import hu.danielwolf.pokeCounter.external.api.dto.ExternalName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalMoveBattleStyle(
  val id: Int,
  val name: String,
  val names: List<ExternalName>,
)