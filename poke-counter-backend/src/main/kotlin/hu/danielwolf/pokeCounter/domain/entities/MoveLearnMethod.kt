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
@Table(name = "move_learn_methods")
data class MoveLearnMethod(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "descriptions", columnDefinition = "jsonb")
    var descriptions: Map<String, String>? = emptyMap(),

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),

    @OneToMany
    @JoinColumn(name = "learn_method_id", referencedColumnName = "id")
    var versionGroups: Set<LearnMethodVersionGroup> = emptySet(),

    @OneToMany
    @JoinColumn(name = "move_learn_method", referencedColumnName = "id")
    var pokemonMoves: Set<PokemonMove> = emptySet(),
)

