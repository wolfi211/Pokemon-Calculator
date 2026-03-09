package hu.danielwolf.pokeCounter.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "pokemon_moves")
data class PokemonMove(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "pokemon_id")
    val pokemonId: Int,

    @Column(name = "move_id")
    val moveId: Int,

    @Column(name = "move_learn_method")
    val moveLearnMethodId: Int,

    @Column(name = "version_group_id")
    val versionGroupId: Int,

    @Column(name = "level_learned_at")
    val levelLearnedAt: Int?,

    @Column(name = "order")
    val order: Int?,
)

