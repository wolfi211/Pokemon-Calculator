package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "pokedex_pokemon")
data class PokedexPokemon(
    @EmbeddedId
    var id: PokedexPokemonId,

    @Column(name = "entry_number")
    var entryNumber: Int,
)

