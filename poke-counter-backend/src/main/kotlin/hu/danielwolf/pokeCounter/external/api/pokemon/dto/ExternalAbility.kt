package hu.danielwolf.pokeCounter.external.api.pokemon.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalEffect
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalVerboseEffect
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalAbility(
    val id: Int,
    val name: String,
    @JsonProperty("is_main_series") val isMainSeries: Boolean,
    val generation: NamedAPIResource,
    val names: List<ExternalName>,
    @JsonProperty("effect_entries") val effectEntries: List<ExternalVerboseEffect>,
    @JsonProperty("effect_changes") val effectChanges: List<ExternalAbilityEffectChange>,
    @JsonProperty("flavor_text_entries") val flavorTextEntries: List<ExternalAbilityFlavorText>,
    val pokemon: List<ExternalAbilityPokemon>,
)

data class ExternalAbilityEffectChange(
    @JsonProperty("effect_entries") val effectEntries: List<ExternalEffect>,
    @JsonProperty("version_group") val versionGroup: NamedAPIResource,
)

data class ExternalAbilityFlavorText(
    @JsonProperty("flavor_text") val flavorText: String,
    val language: NamedAPIResource,
    @JsonProperty("version_group") val versionGroup: NamedAPIResource,
)

data class ExternalAbilityPokemon(
    @JsonProperty("is_hidden") val isHidden: Boolean,
    val slot: Int,
    val pokemon: NamedAPIResource,
)