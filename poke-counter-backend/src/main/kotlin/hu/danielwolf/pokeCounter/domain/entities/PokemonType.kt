package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "pokemon_types")
data class PokemonType(
    @EmbeddedId
    val id: PokemonTypeId,

    @Column(name = "type_id")
    val typeId: Int,
)

