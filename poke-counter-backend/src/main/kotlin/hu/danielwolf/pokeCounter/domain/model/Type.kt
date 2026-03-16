package hu.danielwolf.pokeCounter.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(name = "types")
data class Type(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),

    @OneToMany(mappedBy = "type")
    var pokemonTypes: MutableSet<PokemonType> = mutableSetOf(),

    @OneToMany(mappedBy = "type")
    var moveTypes: MutableSet<MoveType> = mutableSetOf(),

    @OneToMany(mappedBy = "damageFromType")
    var damageFromRelations: MutableSet<TypeRelation> = mutableSetOf(),

    @OneToMany(mappedBy = "damageToType")
    var damageToRelations: MutableSet<TypeRelation> = mutableSetOf(),
)

