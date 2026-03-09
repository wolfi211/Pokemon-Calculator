package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "types")
data class Type(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "name", nullable = false, unique = true)
    val name: String,

    @Column(name = "names", columnDefinition = "jsonb")
    val names: Map<String, String>?,

    @OneToMany
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    val pokemonTypes: Set<PokemonType> = emptySet(),

    @OneToMany
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    val moveTypes: Set<MoveType> = emptySet(),

    @OneToMany
    @JoinColumn(name = "damage_from", referencedColumnName = "id")
    val damageFromRelations: Set<TypeRelation> = emptySet(),

    @OneToMany
    @JoinColumn(name = "damage_to", referencedColumnName = "id")
    val damageToRelations: Set<TypeRelation> = emptySet(),
)

