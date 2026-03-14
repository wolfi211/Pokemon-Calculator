package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class PokemonTypeId(
    @Column(name = "pokemon_id")
    var pokemonId: Int,

    @Column(name = "slot")
    var slot: Int,

    @Column(name = "generation_id")
    var generationId: Int?,
) : Serializable

