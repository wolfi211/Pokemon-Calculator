package hu.danielwolf.pokeCounter.domain.model.pokemon

import hu.danielwolf.pokeCounter.domain.model.games.VersionGroup
import hu.danielwolf.pokeCounter.domain.model.moves.Move
import hu.danielwolf.pokeCounter.domain.model.moves.MoveLearnMethod
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "pokemon_moves")
data class PokemonMove(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokemon_moves_id_seq")
    @SequenceGenerator(name = "pokemon_moves_id_seq", sequenceName = "pokemon_moves_id_seq", allocationSize = 1)
    var id: Int = 0,

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

