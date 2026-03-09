package hu.danielwolf.pokeCounter.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "pokemon")
data class Pokemon(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "name", nullable = false, unique = true)
    val name: String,

    @Column(name = "base_experience")
    val baseExperience: Int?,

    @Column(name = "height")
    val height: Int?,

    @Column(name = "is_default")
    val isDefault: Boolean?,

    @Column(name = "\"order\"")
    val order: Int?,

    @Column(name = "weight")
    val weight: Int?,

    @Column(name = "sprite")
    val sprite: String?,

    @Column(name = "cry")
    val cry: String?,

    @Column(name = "species_id")
    val speciesId: Int?,
)

