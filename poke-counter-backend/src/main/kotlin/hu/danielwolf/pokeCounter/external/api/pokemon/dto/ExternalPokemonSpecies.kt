package hu.danielwolf.pokeCounter.external.api.pokemon.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.APIResource
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalDescription
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalFlavorText
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalPokemonSpecies(
    val id: Int,
    val name: String,
    val order: Int,
    @JsonProperty("gender_rate") val genderRate: Int,
    @JsonProperty("capture_rate") val captureRate: Int,
    @JsonProperty("base_happiness") val baseHappiness: Int,
    @JsonProperty("is_baby") val isBaby: Boolean,
    @JsonProperty("is_legendary") val isLegendary: Boolean,
    @JsonProperty("is_mythical") val isMythical: Boolean,
    @JsonProperty("hatch_counter") val hatchCounter: Int,
    @JsonProperty("has_gender_differences") val hasGenderDifferences: Boolean,
    @JsonProperty("forms_switchable") val formsSwitchable: Boolean,
    @JsonProperty("growth_rate") val growthRate: NamedAPIResource,
    @JsonProperty("pokedex_numbers") val pokedexNumbers: List<ExternalPokemonSpeciesDexEntry>,
    @JsonProperty("egg_groups") val eggGroups: List<NamedAPIResource>,
    val color: NamedAPIResource,
    val shape: NamedAPIResource,
    @JsonProperty("evolves_from_species") val evolvesFromSpecies: NamedAPIResource?,
    @JsonProperty("evolution_chain") val evolutionChain: APIResource,
    val habitat: NamedAPIResource?,
    val generation: NamedAPIResource,
    val names: List<ExternalName>,
    @JsonProperty("pal_park_encounters") val palParkEncounters: List<ExternalPalParkEncounterArea>,
    @JsonProperty("flavor_text_entries") val flavorTextEntries: List<ExternalFlavorText>,
    @JsonProperty("form_descriptions") val formDescriptions: List<ExternalDescription>,
    val genera: List<ExternalGenus>,
    val varieties: List<ExternalPokemonSpeciesVariety>,
)

data class ExternalGenus(
    val genus: String,
    val language: NamedAPIResource,
)

data class ExternalPokemonSpeciesDexEntry(
    @JsonProperty("entry_number") val entryNumber: Int,
    val pokedex: NamedAPIResource,
)

data class ExternalPalParkEncounterArea(
    @JsonProperty("base_score") val baseScore: Int,
    val rate: Int,
    val area: NamedAPIResource,
)

data class ExternalPokemonSpeciesVariety(
    @JsonProperty("is_default") val isDefault: Boolean,
    val pokemon: NamedAPIResource,
)