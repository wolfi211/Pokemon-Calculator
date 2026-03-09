package hu.danielwolf.pokeCounter.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "damage_classes")
data class DamageClass(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "name", nullable = false, unique = true)
    val name: String,

    @Column(name = "names", columnDefinition = "jsonb")
    val names: Map<String, String>?,

    @Column(name = "descriptions", columnDefinition = "jsonb")
    val descriptions: Map<String, String>?,

    @OneToMany
    @JoinColumn(name = "damage_class", referencedColumnName = "id")
    val moves: Set<Move> = emptySet(),
)

