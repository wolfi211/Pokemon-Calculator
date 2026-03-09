package hu.danielwolf.pokeCounter.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class PokemonTypeId(
    @Column(name = "pokemon_id")
    val pokemonId: Int,

    @Column(name = "slot")
    val slot: Int,

    @Column(name = "generation_id")
    val generationId: Int,
) : Serializable

