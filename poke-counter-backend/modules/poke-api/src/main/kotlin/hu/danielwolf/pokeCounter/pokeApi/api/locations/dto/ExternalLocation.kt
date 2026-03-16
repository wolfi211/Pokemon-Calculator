@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.locations.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalGenerationGameIndex
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalLocation(
  val id: Int,
  val name: String,
  val region: NamedAPIResource,
  val names: List<ExternalName>,
  @param:JsonProperty("game_indices") val gameIndices: List<ExternalGenerationGameIndex>,
  val areas: List<NamedAPIResource>
)