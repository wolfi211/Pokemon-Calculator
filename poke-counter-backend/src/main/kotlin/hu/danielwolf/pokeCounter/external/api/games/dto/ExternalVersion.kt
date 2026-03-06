package hu.danielwolf.pokeCounter.external.api.games.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalVersion(
    val id: Int,
    val name: String,
    val names: List<ExternalName>,
    val versionGroup: NamedAPIResource,
)
