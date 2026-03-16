package hu.danielwolf.pokeCounter.external.api.pokemon.dto

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalName
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResource
import com.fasterxml.jackson.annotation.JsonProperty
data class ExternalPokeathlonStat (
    val id: Int,
    val name: String,
    val names: List<ExternalName>,
    @JsonProperty("affecting_natures") val affectingNatures: ExternalNaturePokeathlonStatAffectSets,
)

data class ExternalNaturePokeathlonStatAffectSets(
    val increase: List<ExternalNaturePokeathlonStatAffect>,
    val decrease: List<ExternalNaturePokeathlonStatAffect>,
)

data class ExternalNaturePokeathlonStatAffect(
    @JsonProperty("max_change") val maxChange: Int,
    val nature: NamedAPIResource,
)