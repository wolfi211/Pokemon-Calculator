package hu.danielwolf.pokeCounter.external.api.dto.pokemon

import hu.danielwolf.pokeCounter.external.api.dto.APIResource
import hu.danielwolf.pokeCounter.external.api.dto.ExternalDescription
import hu.danielwolf.pokeCounter.external.api.dto.ExternalFlavorText
import hu.danielwolf.pokeCounter.external.api.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.dto.NamedAPIResource
import kotlinx.serialization.Serializable

@Serializable
data class ExternalPokemonSpecies(
  val id: Int,
  val name: String,
  val order: Int,
  val genderRate: Int,
  val captureRate: Int,
  val baseHappiness: Int,
  val isBaby: Boolean,
  val isLegendary: Boolean,
  val isMythical: Boolean,
  val hatchCounter: Boolean,
  val hasGenderDifferences: Boolean,
  val formsSwitchable: Boolean,
  val growthRate: NamedAPIResource,
  val pokedexNumbers: List<ExternalPokemonSpeciesDexEntry>,
  val eggGroups: List<NamedAPIResource>,
  val color: NamedAPIResource,
  val shape: NamedAPIResource,
  val evolvesFromSpecies: NamedAPIResource,
  val evolutionChain: APIResource,
  val habitat: NamedAPIResource,
  val generation: NamedAPIResource,
  val names: List<ExternalName>,
  val palParkEncounters: List<ExternalPalParkEncounterArea>,
  val flavorTextEntries: List<ExternalFlavorText>,
  val formDescriptions: List<ExternalDescription>,
  val genera: List<ExternalGenus>,
  val varieties: List<ExternalPokemonSpeciesVariety>,
)

@Serializable
data class ExternalGenus(
  val genus: String,
  val language: NamedAPIResource,
)

@Serializable
data class ExternalPokemonSpeciesDexEntry(
  val entryNumber: Int,
  val pokedex: NamedAPIResource,
)

@Serializable
data class ExternalPalParkEncounterArea(
  val baseScore: Int,
  val rate: Int,
  val area: NamedAPIResource,
)

@Serializable
data class ExternalPokemonSpeciesVariety(
  val isDefault: Boolean,
  val pokemon: NamedAPIResource,
)