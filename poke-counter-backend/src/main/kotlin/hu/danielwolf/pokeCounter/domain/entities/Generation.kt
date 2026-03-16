package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(name = "generations")
data class Generation(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),

    @OneToMany(mappedBy = "mainGeneration")
    var regions: MutableSet<Region> = mutableSetOf(),

    @OneToMany(mappedBy = "generation")
    var versionGroups: MutableSet<VersionGroup> = mutableSetOf(),

    @OneToMany(mappedBy = "generation")
    var abilities: MutableSet<Ability> = mutableSetOf(),

    @OneToMany(mappedBy = "generation")
    var moves: MutableSet<Move> = mutableSetOf(),

    @OneToMany(mappedBy = "generation")
    var typeRelations: MutableSet<TypeRelation> = mutableSetOf(),

    @OneToMany(mappedBy = "generation")
    var pokemonAbilities: MutableSet<PokemonAbility> = mutableSetOf(),

    @OneToMany(mappedBy = "generation")
    var pokemonTypes: MutableSet<PokemonType> = mutableSetOf(),

    @OneToMany(mappedBy = "generation")
    var pokemonStats: MutableSet<PokemonStat> = mutableSetOf(),
)

