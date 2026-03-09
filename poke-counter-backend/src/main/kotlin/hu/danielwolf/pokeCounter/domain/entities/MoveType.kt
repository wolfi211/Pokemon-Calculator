package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "move_types")
data class MoveType(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "move_id")
    val moveId: Int,

    @Column(name = "accuracy")
    val accuracy: Int?,

    @Column(name = "effect_chance")
    val effectChance: Int?,

    @Column(name = "pp")
    val pp: Int?,

    @Column(name = "power")
    val power: Int?,

    @Column(name = "effect_entries", columnDefinition = "jsonb")
    val effectEntries: Map<String, String>?,

    @Column(name = "type_id")
    val typeId: Int?,

    @Column(name = "version_group_id")
    val versionGroupId: Int?,
)

