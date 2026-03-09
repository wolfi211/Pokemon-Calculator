package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "pokemon")
data class Pokemon(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @Column(name = "base_experience")
    var baseExperience: Int?,

    @Column(name = "height")
    var height: Int?,

    @Column(name = "is_default")
    var isDefault: Boolean?,

    @Column(name = "order")
    var order: Int?,

    @Column(name = "weight")
    var weight: Int?,

    @Column(name = "sprite")
    var sprite: String?,

    @Column(name = "cry")
    var cry: String?,

    @Column(name = "species_id")
    var speciesId: Int?,

    @OneToMany
    @JoinColumn(name = "pokemon_id", referencedColumnName = "id")
    var types: Set<PokemonType> = emptySet(),

    @OneToMany
    @JoinColumn(name = "pokemon_id", referencedColumnName = "id")
    var abilities: Set<PokemonAbility> = emptySet(),

    @OneToMany
    @JoinColumn(name = "pokemon_id", referencedColumnName = "id")
    var moves: Set<PokemonMove> = emptySet(),

    @OneToMany
    @JoinColumn(name = "pokemon_id", referencedColumnName = "id")
    var stats: Set<PokemonStat> = emptySet(),

    @OneToMany
    @JoinColumn(name = "pokemon_id", referencedColumnName = "id")
    var forms: Set<PokemonForm> = emptySet(),
)

