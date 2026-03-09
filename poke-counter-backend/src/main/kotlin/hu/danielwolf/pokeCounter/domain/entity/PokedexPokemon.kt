package hu.danielwolf.pokeCounter.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "pokedex_pokemon")
data class PokedexPokemon(
    @EmbeddedId
    val id: PokedexPokemonId,

    @Column(name = "entry_number")
    val entryNumber: Int,
)

