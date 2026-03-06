package hu.danielwolf.pokeCounter.external.api.dto.locations

import hu.danielwolf.pokeCounter.external.api.dto.ExternalGenerationGameIndex
import hu.danielwolf.pokeCounter.external.api.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalLocation(
  val id: Int,
  val name: String,
  val region: NamedAPIResource,
  val names: List<ExternalName>,
  val gameIndices: List<ExternalGenerationGameIndex>,
  val areas: List<NamedAPIResource>
)
