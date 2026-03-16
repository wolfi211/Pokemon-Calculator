package hu.danielwolf.pokeCounter.external.api.pokemon.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalPokemonForm (
    val id: Int,
    val name: String,
    val order: Int,
    @JsonProperty("form_order") val formOrder: Int,
    @JsonProperty("is_default") val isDefault: Boolean,
    @JsonProperty("is_battle_only") val isBattleOnly: Boolean,
    @JsonProperty("is_mega") val isMega: Boolean,
    @JsonProperty("form_name") val formName: String,
    val pokemon: NamedAPIResource,
    val types: List<ExternalPokemonFormType>,
    val sprites: ExternalPokemonFormSprites,
    @JsonProperty("version_group") val versionGroup: NamedAPIResource,
    val names: List<ExternalName>,
    @JsonProperty("form_names") val formNames: List<ExternalName>,
)

data class ExternalPokemonFormSprites(
  @JsonProperty("front_default") val frontDefault: String?,
  @JsonProperty("front_shiny") val frontShiny: String?,
  @JsonProperty("back_default") val backDefault: String?,
  @JsonProperty("back_shiny") val backShiny: String?,
)