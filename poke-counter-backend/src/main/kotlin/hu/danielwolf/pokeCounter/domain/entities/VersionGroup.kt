package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
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

    @Column(name = "generation_id")
    var generationId: Int?,

    @OneToMany
    @JoinColumn(name = "version_group_id", referencedColumnName = "id")
    var versions: Set<Version> = emptySet(),

    @OneToMany
    @JoinColumn(name = "version_group_id", referencedColumnName = "id")
    var pokedexVersionGroups: Set<PokedexVersionGroup> = emptySet(),

    @OneToMany
    @JoinColumn(name = "version_group_id", referencedColumnName = "id")
    var learnMethodVersionGroups: Set<LearnMethodVersionGroup> = emptySet(),

    @OneToMany
    @JoinColumn(name = "version_group_id", referencedColumnName = "id")
    var moveTypes: Set<MoveType> = emptySet(),

    @OneToMany
    @JoinColumn(name = "version_group_id", referencedColumnName = "id")
    var pokemonMoves: Set<PokemonMove> = emptySet(),

    @OneToMany
    @JoinColumn(name = "version_group", referencedColumnName = "id")
    var pokemonForms: Set<PokemonForm> = emptySet(),
)

