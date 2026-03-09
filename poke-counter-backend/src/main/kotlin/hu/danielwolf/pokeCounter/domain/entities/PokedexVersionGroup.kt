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
    var id: Int,

    @Column(name = "pokedex_id")
    var pokedexId: Int,

    @Column(name = "version_group_id")
    var versionGroupId: Int,
)

