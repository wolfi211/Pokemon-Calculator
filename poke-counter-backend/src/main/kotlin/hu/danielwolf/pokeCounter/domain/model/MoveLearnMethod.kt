package hu.danielwolf.pokeCounter.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(name = "move_learn_methods")
data class MoveLearnMethod(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "descriptions", columnDefinition = "jsonb")
    var descriptions: Map<String, String>? = emptyMap(),

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),

    @OneToMany(mappedBy = "learnMethod")
    var versionGroups: MutableSet<LearnMethodVersionGroup> = mutableSetOf(),

    @OneToMany(mappedBy = "moveLearnMethod")
    var pokemonMoves: MutableSet<PokemonMove> = mutableSetOf(),
)

