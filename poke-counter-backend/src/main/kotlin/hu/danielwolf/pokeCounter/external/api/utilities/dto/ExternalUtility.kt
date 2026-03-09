package hu.danielwolf.pokeCounter.external.api.utilities.dto

import hu.danielwolf.pokeCounter.domain.entity.LocalizedName
import kotlinx.serialization.Serializable

@Serializable
data class NamedAPIResource(
  val name: String,
  val url: String,
)

@Serializable
data class APIResource(
  val url: String,
)

@Serializable
data class NamedApiResourceList(
  val count: Int,
  val next: String,
  val previous: String,
  val results: List<NamedAPIResource>
)

@Serializable
data class UnnamedApiResourceList(
  val count: Int,
  val next: String,
  val previous: String,
  val results: List<APIResource>
)

@Serializable
data class ExternalName(
  val name: String,
  val language: NamedAPIResource
)

@Serializable
data class ExternalDescription(
  val description: String,
  val language: NamedAPIResource
)

@Serializable
data class ExternalEffect(
  val effect: String,
  val language: NamedAPIResource
)

@Serializable
data class ExternalEncounter(
  val minLevel: Int,
  val maxLevel: Int,
  val conditionValues: List<NamedAPIResource>,
  val chance: Int,
  val method: NamedAPIResource
)

@Serializable
data class ExternalFlavorText(
  val flavorText: String,
  val language: NamedAPIResource,
  val version: NamedAPIResource,
)

@Serializable
data class ExternalGenerationGameIndex(
  val gameIndex: Int,
  val generation: NamedAPIResource,
)

@Serializable
data class ExternalMachineVersionDetail(
  val machine: APIResource,
  val versionGroup: NamedAPIResource,
)

@Serializable
data class ExternalVerboseEffect(
  val effect: String,
  val shortEffect: String,
  val language: NamedAPIResource,
)

@Serializable
data class ExternalVersionEncounterDetail(
  val version: NamedAPIResource,
  val maxChance: Int,
  val encounterDetails: List<ExternalEncounter>,
)

@Serializable
data class ExternalVersionGameIndex(
  val gameIndex: Int,
  val version: NamedAPIResource,
)

@Serializable
data class ExternalVersionGroupFlavorText(
  val text: String,
  val language: NamedAPIResource,
  val versionGroup: NamedAPIResource,
)

@Serializable
data class ExternalLanguage(
  val id: Int,
  val name: String,
  val official: Boolean,
  val iso639: String,
  val iso3166: String,
  val names: List<ExternalName>,
)

@Serializable
data class PageRequest(
  val offset: Int = 0,
  val limit: Int = 20,
)