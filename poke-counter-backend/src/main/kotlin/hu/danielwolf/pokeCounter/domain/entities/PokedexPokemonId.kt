package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class PokedexPokemonId(
    @Column(name = "pokedex_id")
    var pokedexId: Int,

    @Column(name = "species_id")
    var speciesId: Int,
) : Serializable

