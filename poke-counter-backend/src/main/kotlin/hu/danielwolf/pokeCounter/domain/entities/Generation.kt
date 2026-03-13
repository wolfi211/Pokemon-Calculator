package hu.danielwolf.pokeCounter.domain.entities

import hu.danielwolf.pokeCounter.config.JsonMapConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "generations")
data class Generation(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @Convert(converter = JsonMapConverter::class)
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

