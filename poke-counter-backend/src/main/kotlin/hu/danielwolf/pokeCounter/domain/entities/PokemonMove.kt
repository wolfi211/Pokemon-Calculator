package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "pokemon_moves")
data class PokemonMove(
    @Id
    var id: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokemon_id")
    var pokemon: Pokemon,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "move_id")
    var move: Move,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "move_learn_method")
    var moveLearnMethod: MoveLearnMethod,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "version_group_id")
    var versionGroup: VersionGroup,

    @Column(name = "level_learned_at")
    var levelLearnedAt: Int?,

    @Column(name = "order")
    var order: Int?,
)

