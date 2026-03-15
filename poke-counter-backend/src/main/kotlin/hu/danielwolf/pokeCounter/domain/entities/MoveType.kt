package hu.danielwolf.pokeCounter.domain.entities

import hu.danielwolf.pokeCounter.config.JsonMapConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
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
@Table(name = "move_types")
data class MoveType(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "move_types_id_seq")
    @SequenceGenerator(name = "move_types_id_seq", sequenceName = "move_types_id_seq", allocationSize = 1)
    var id: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "move_id")
    var move: Move,

    @Column(name = "accuracy")
    var accuracy: Int?,

    @Column(name = "effect_chance")
    var effectChance: Int?,

    @Column(name = "pp")
    var pp: Int?,

    @Column(name = "power")
    var power: Int?,

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "effect_entries", columnDefinition = "jsonb")
    var effectEntries: Map<String, String>? = emptyMap(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    var type: Type?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "version_group_id")
    var versionGroup: VersionGroup?,
)

