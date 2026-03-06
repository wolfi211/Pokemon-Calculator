package hu.danielwolf.pokeCounter.external.api.pokemon.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalNature (
    val id: Int,
    val name: String,
    val decreasedStat: NamedAPIResource,
    val increasedStat: NamedAPIResource,
    val hatesFlavor: NamedAPIResource,
    val likesFlavor: NamedAPIResource,
    val pokeathlonStatChanges: List<ExternalNatureStatChange>,
    val moveBattleStylePreferences: List<ExternalMoveBattleStylePreference>,
    val names: List<ExternalName>
)

@Serializable
data class ExternalNatureStatChange(
    val maxChange: Int,
    val pokeathlonStat: NamedAPIResource,
)

@Serializable
data class ExternalMoveBattleStylePreference(
    val lowHpPreference: Int,
    val highHpPreference: Int,
    val moveBattleStyle: NamedAPIResource,
)
