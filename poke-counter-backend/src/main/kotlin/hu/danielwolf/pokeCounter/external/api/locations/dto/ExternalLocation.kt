package hu.danielwolf.pokeCounter.external.api.locations.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalGenerationGameIndex
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
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
