package hu.danielwolf.pokeCounter.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "ability")
data class Ability(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "name", nullable = false, unique = true)
    val name: String,

    @Column(name = "is_main_series")
    val isMainSeries: Boolean?,

    @Column(name = "generation")
    val generationId: Int?,

    @Column(name = "names", columnDefinition = "jsonb")
    val names: Map<String, String>?,

    @Column(name = "flavor_texts", columnDefinition = "jsonb")
    val flavorTexts: Map<String, String>?,
)

