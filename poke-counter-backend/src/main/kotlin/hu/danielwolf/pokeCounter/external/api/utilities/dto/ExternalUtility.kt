package hu.danielwolf.pokeCounter.external.api.utilities.dto

import com.fasterxml.jackson.annotation.JsonProperty
data class NamedAPIResource(
  val name: String,
  val url: String,
)

data class APIResource(
  val url: String,
)

data class NamedAPIResourceList(
  val count: Int,
  val next: String?,
  val previous: String?,
  val results: List<NamedAPIResource>
)

data class UnnamedAPIResourceList(
  val count: Int,
  val next: String,
  val previous: String,
  val results: List<APIResource>
)

data class ExternalName(
  val name: String,
  val language: NamedAPIResource
)

data class ExternalDescription(
  val description: String,
  val language: NamedAPIResource
)

data class ExternalEffect(
  val effect: String,
  val language: NamedAPIResource
)

data class ExternalEncounter(
  @JsonProperty("min_level") val minLevel: Int,
  @JsonProperty("max_level") val maxLevel: Int,
  @JsonProperty("condition_values") val conditionValues: List<NamedAPIResource>,
  val chance: Int,
  val method: NamedAPIResource
)

data class ExternalFlavorText(
  @JsonProperty("flavor_text") val flavorText: String,
  val language: NamedAPIResource,
  val version: NamedAPIResource,
)

data class ExternalGenerationGameIndex(
  @JsonProperty("game_index") val gameIndex: Int,
  val generation: NamedAPIResource,
)

data class ExternalMachineVersionDetail(
  val machine: APIResource,
  @JsonProperty("version_group") val versionGroup: NamedAPIResource,
)

data class ExternalVerboseEffect(
  val effect: String,
  @JsonProperty("short_effect") val shortEffect: String,
  val language: NamedAPIResource,
)

data class ExternalVersionEncounterDetail(
  val version: NamedAPIResource,
  @JsonProperty("max_chance") val maxChance: Int,
  @JsonProperty("encounter_details") val encounterDetails: List<ExternalEncounter>,
)

data class ExternalVersionGameIndex(
  @JsonProperty("game_index") val gameIndex: Int,
  val version: NamedAPIResource,
)

data class ExternalVersionGroupFlavorText(
  val text: String,
  val language: NamedAPIResource,
  @JsonProperty("version_group") val versionGroup: NamedAPIResource,
)

data class ExternalLanguage(
  val id: Int,
  val name: String,
  val official: Boolean,
  val iso639: String,
  val iso3166: String,
  val names: List<ExternalName>,
)