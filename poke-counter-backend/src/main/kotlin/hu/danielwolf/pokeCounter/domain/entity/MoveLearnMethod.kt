package hu.danielwolf.pokeCounter.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "move_learn_methods")
data class MoveLearnMethod(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "name", nullable = false, unique = true)
    val name: String,

    @Column(name = "descriptions", columnDefinition = "jsonb")
    val descriptions: Map<String, String>?,

    @Column(name = "names", columnDefinition = "jsonb")
    val names: Map<String, String>?,

    @OneToMany
    @JoinColumn(name = "learn_method_id", referencedColumnName = "id")
    val versionGroups: Set<LearnMethodVersionGroup> = emptySet(),

    @OneToMany
    @JoinColumn(name = "move_learn_method", referencedColumnName = "id")
    val pokemonMoves: Set<PokemonMove> = emptySet(),
)

