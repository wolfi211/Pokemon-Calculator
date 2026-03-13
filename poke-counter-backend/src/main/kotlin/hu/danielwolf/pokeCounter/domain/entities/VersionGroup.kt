package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "version_groups")
data class VersionGroup(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @Column(name = "order")
    var order: Int?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generation_id")
    var generation: Generation?,

    @OneToMany(mappedBy = "versionGroup")
    var versions: MutableSet<Version> = mutableSetOf(),

    @OneToMany(mappedBy = "versionGroup")
    var pokedexVersionGroups: MutableSet<PokedexVersionGroup> = mutableSetOf(),

    @OneToMany(mappedBy = "versionGroup")
    var learnMethodVersionGroups: MutableSet<LearnMethodVersionGroup> = mutableSetOf(),

    @OneToMany(mappedBy = "versionGroup")
    var moveTypes: MutableSet<MoveType> = mutableSetOf(),

    @OneToMany(mappedBy = "versionGroup")
    var pokemonMoves: MutableSet<PokemonMove> = mutableSetOf(),

    @OneToMany(mappedBy = "versionGroup")
    var pokemonForms: MutableSet<PokemonForm> = mutableSetOf(),
)

