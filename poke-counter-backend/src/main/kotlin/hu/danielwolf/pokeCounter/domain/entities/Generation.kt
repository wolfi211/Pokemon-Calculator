package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
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
    val id: Int,

    @Column(name = "name", nullable = false, unique = true)
    val name: String,

    @Column(name = "names", columnDefinition = "jsonb")
    val names: Map<String, String>?,

    @OneToMany
    @JoinColumn(name = "main_generation", referencedColumnName = "id")
    val regions: Set<Region> = emptySet(),

    @OneToMany
    @JoinColumn(name = "generation_id", referencedColumnName = "id")
    val versionGroups: Set<VersionGroup> = emptySet(),

    @OneToMany
    @JoinColumn(name = "generation", referencedColumnName = "id")
    val abilities: Set<Ability> = emptySet(),

    @OneToMany
    @JoinColumn(name = "generation", referencedColumnName = "id")
    val moves: Set<Move> = emptySet(),

    @OneToMany
    @JoinColumn(name = "generation", referencedColumnName = "id")
    val typeRelations: Set<TypeRelation> = emptySet(),

    @OneToMany
    @JoinColumn(name = "generation_id", referencedColumnName = "id")
    val pokemonAbilities: Set<PokemonAbility> = emptySet(),

    @OneToMany
    @JoinColumn(name = "generation_id", referencedColumnName = "id")
    val pokemonTypes: Set<PokemonType> = emptySet(),

    @OneToMany
    @JoinColumn(name = "generation_id", referencedColumnName = "id")
    val pokemonStats: Set<PokemonStat> = emptySet(),
)

