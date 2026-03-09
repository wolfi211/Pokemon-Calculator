package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "pokemon_moves")
data class PokemonMove(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "pokemon_id")
    var pokemonId: Int,

    @Column(name = "move_id")
    var moveId: Int,

    @Column(name = "move_learn_method")
    var moveLearnMethodId: Int,

    @Column(name = "version_group_id")
    var versionGroupId: Int,

    @Column(name = "level_learned_at")
    var levelLearnedAt: Int?,

    @Column(name = "order")
    var order: Int?,
)

