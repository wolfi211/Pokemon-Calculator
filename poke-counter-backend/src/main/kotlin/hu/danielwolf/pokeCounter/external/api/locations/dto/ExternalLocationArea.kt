package hu.danielwolf.pokeCounter.external.api.locations.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalVersionEncounterDetail
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource

data class ExternalLocationArea(
    val id: Int,
    val name: String,
    val gameIndex: Int,
    val encounterMethodRates: List<ExternalMethodRate>,
    val location: NamedAPIResource,
    val names: List<ExternalName>,
    val pokemonEncounters: List<ExternalPokemonEncounter>,
)

data class ExternalMethodRate(
    val encounterMethod: NamedAPIResource,
    val versionDetails: List<ExternalEncounterVersionDetail>,
)

data class ExternalEncounterVersionDetail(
    val rate: Int,
    val version: NamedAPIResource,
)

data class ExternalPokemonEncounter(
    val pokemon: NamedAPIResource,
    val versionDetails: List<ExternalVersionEncounterDetail>,
)