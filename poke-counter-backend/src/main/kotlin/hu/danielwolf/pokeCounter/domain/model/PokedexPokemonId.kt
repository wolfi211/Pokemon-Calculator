package hu.danielwolf.pokeCounter.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class PokedexPokemonId(
    @Column(name = "pokedex_id")
    var pokedexId: Int,

    @Column(name = "version_group_id")
    var versionGroupId: Int,

    @Column(name = "pokemon_id")
    var pokemonId: Int,
) : Serializable
