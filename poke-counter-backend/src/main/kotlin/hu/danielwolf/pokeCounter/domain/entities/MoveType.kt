package hu.danielwolf.pokeCounter.domain.entities

import hu.danielwolf.pokeCounter.config.JsonMapConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "move_types")
data class MoveType(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "move_id")
    var moveId: Int,

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

    @Column(name = "type_id")
    var typeId: Int?,

    @Column(name = "version_group_id")
    var versionGroupId: Int?,
)

