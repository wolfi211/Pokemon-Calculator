package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "pokedexes")
data class Pokedex(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "name", nullable = false, unique = true)
    val name: String,

    @Column(name = "is_main_series")
    val isMainSeries: Boolean?,

    @Column(name = "descriptions", columnDefinition = "jsonb")
    val descriptions: Map<String, String>?,

    @Column(name = "names", columnDefinition = "jsonb")
    val names: Map<String, String>?,

    @Column(name = "region")
    val regionId: Int?,

    @OneToMany
    @JoinColumn(name = "pokedex_id", referencedColumnName = "id")
    val entries: Set<PokedexPokemon> = emptySet(),
)

