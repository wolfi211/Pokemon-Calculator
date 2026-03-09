package hu.danielwolf.pokeCounter.domain.entities

import hu.danielwolf.pokeCounter.config.JsonMapConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
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
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @Column(name = "priority")
    var priority: Int?,

    @Column(name = "damage_class")
    var damageClassId: Int?,

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "flavor_texts", columnDefinition = "jsonb")
    var flavorTexts: Map<String, String>? = emptyMap(),

    @Column(name = "generation", columnDefinition = "integer")
    var generationId: Int?,

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),

    @OneToMany
    @JoinColumn(name = "move_id", referencedColumnName = "id")
    var types: Set<MoveType> = emptySet(),

    @OneToMany
    @JoinColumn(name = "move_id", referencedColumnName = "id")
    var pokemonMoves: Set<PokemonMove> = emptySet(),
)

