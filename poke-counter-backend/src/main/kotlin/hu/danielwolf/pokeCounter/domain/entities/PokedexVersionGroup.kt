package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "pokedex_version_group")
data class PokedexVersionGroup(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "pokedex_id")
    val pokedexId: Int,

    @Column(name = "version_group_id")
    val versionGroupId: Int,
)

