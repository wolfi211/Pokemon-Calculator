package hu.danielwolf.pokeCounter.domain.entity

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
    val id: Int,

    @Column(name = "name", nullable = false, unique = true)
    val name: String,

    @Column(name = "order")
    val order: Int?,

    @Column(name = "generation_id")
    val generationId: Int?,

    @OneToMany
    @JoinColumn(name = "version_group_id", referencedColumnName = "id")
    val versions: Set<Version> = emptySet(),

    @OneToMany
    @JoinColumn(name = "version_group_id", referencedColumnName = "id")
    val pokedexVersionGroups: Set<PokedexVersionGroup> = emptySet(),

    @OneToMany
    @JoinColumn(name = "version_group_id", referencedColumnName = "id")
    val learnMethodVersionGroups: Set<LearnMethodVersionGroup> = emptySet(),

    @OneToMany
    @JoinColumn(name = "version_group_id", referencedColumnName = "id")
    val moveTypes: Set<MoveType> = emptySet(),

    @OneToMany
    @JoinColumn(name = "version_group_id", referencedColumnName = "id")
    val pokemonMoves: Set<PokemonMove> = emptySet(),

    @OneToMany
    @JoinColumn(name = "version_group", referencedColumnName = "id")
    val pokemonForms: Set<PokemonForm> = emptySet(),
)

