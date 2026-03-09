package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "moves")
data class Move(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "name", nullable = false, unique = true)
    val name: String,

    @Column(name = "priority")
    val priority: Int?,

    @Column(name = "damage_class")
    val damageClassId: Int?,

    @Column(name = "flavor_texts", columnDefinition = "jsonb")
    val flavorTexts: Map<String, String>?,

    @Column(name = "generation", columnDefinition = "integer")
    val generationId: Int?,

    @Column(name = "names", columnDefinition = "jsonb")
    val names: Map<String, String>?,

    @OneToMany
    @JoinColumn(name = "move_id", referencedColumnName = "id")
    val types: Set<MoveType> = emptySet(),

    @OneToMany
    @JoinColumn(name = "move_id", referencedColumnName = "id")
    val pokemonMoves: Set<PokemonMove> = emptySet(),
)

