@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto

import com.fasterxml.jackson.annotation.JsonProperty
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.APIResource
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalDescription
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalFlavorText
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResource

data class ExternalPokemonSpecies(
  val id: Int,
  val name: String,
  val order: Int,
  @param:JsonProperty("gender_rate") val genderRate: Int,
  @param:JsonProperty("capture_rate") val captureRate: Int,
  @param:JsonProperty("base_happiness") val baseHappiness: Int,
  @param:JsonProperty("is_baby") val isBaby: Boolean,
  @param:JsonProperty("is_legendary") val isLegendary: Boolean,
  @param:JsonProperty("is_mythical") val isMythical: Boolean,
  @param:JsonProperty("hatch_counter") val hatchCounter: Int,
  @param:JsonProperty("has_gender_differences") val hasGenderDifferences: Boolean,
  @param:JsonProperty("forms_switchable") val formsSwitchable: Boolean,
  @param:JsonProperty("growth_rate") val growthRate: NamedAPIResource,
  @param:JsonProperty("pokedex_numbers") val pokedexNumbers: List<ExternalPokemonSpeciesDexEntry>,
  @param:JsonProperty("egg_groups") val eggGroups: List<NamedAPIResource>,
  val color: NamedAPIResource,
  val shape: NamedAPIResource,
  @param:JsonProperty("evolves_from_species") val evolvesFromSpecies: NamedAPIResource?,
  @param:JsonProperty("evolution_chain") val evolutionChain: APIResource,
  val habitat: NamedAPIResource?,
  val generation: NamedAPIResource,
  val names: List<ExternalName>,
  @param:JsonProperty("pal_park_encounters") val palParkEncounters: List<ExternalPalParkEncounterArea>,
  @param:JsonProperty("flavor_text_entries") val flavorTextEntries: List<ExternalFlavorText>,
  @param:JsonProperty("form_descriptions") val formDescriptions: List<ExternalDescription>,
  val genera: List<ExternalGenus>,
  val varieties: List<ExternalPokemonSpeciesVariety>,
)

data class ExternalGenus(
  val genus: String,
  val language: NamedAPIResource,
)

data class ExternalPokemonSpeciesDexEntry(
  @param:JsonProperty("entry_number") val entryNumber: Int,
  val pokedex: NamedAPIResource,
)

data class ExternalPalParkEncounterArea(
  @param:JsonProperty("base_score") val baseScore: Int,
  val rate: Int,
  val area: NamedAPIResource,
)

data class ExternalPokemonSpeciesVariety(
  @param:JsonProperty("is_default") val isDefault: Boolean,
  val pokemon: NamedAPIResource,
)