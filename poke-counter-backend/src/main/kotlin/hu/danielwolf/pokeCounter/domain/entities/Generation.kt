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

    @OneToMany
    @JoinColumn(name = "mainGeneration")
    var regions: MutableSet<Region> = mutableSetOf(),

    @OneToMany
    @JoinColumn(name = "generation_id", referencedColumnName = "id")
    var versionGroups: Set<VersionGroup> = emptySet(),

    @OneToMany
    @JoinColumn(name = "generation", referencedColumnName = "id")
    var abilities: Set<Ability> = emptySet(),

    @OneToMany
    @JoinColumn(name = "generation", referencedColumnName = "id")
    var moves: Set<Move> = emptySet(),

    @OneToMany
    @JoinColumn(name = "generation", referencedColumnName = "id")
    var typeRelations: Set<TypeRelation> = emptySet(),

    @OneToMany
    @JoinColumn(name = "generation_id", referencedColumnName = "id")
    var pokemonAbilities: Set<PokemonAbility> = emptySet(),

    @OneToMany
    @JoinColumn(name = "generation_id", referencedColumnName = "id")
    var pokemonTypes: Set<PokemonType> = emptySet(),

    @OneToMany
    @JoinColumn(name = "generation_id", referencedColumnName = "id")
    var pokemonStats: Set<PokemonStat> = emptySet(),
)

