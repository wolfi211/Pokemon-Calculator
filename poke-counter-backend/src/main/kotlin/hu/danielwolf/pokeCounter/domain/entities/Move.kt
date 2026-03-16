package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "damage_class")
    var damageClass: DamageClass?,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "flavor_texts", columnDefinition = "jsonb")
    var flavorTexts: Map<String, String>? = emptyMap(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generation")
    var generation: Generation?,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),

    @OneToMany(mappedBy = "move")
    var types: MutableSet<MoveType> = mutableSetOf(),

    @OneToMany(mappedBy = "move")
    var pokemonMoves: MutableSet<PokemonMove> = mutableSetOf(),
)

