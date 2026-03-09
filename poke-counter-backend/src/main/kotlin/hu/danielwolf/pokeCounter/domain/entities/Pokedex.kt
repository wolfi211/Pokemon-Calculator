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
@Table(name = "pokedexes")
data class Pokedex(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @Column(name = "is_main_series")
    var isMainSeries: Boolean?,

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "descriptions", columnDefinition = "jsonb")
    var descriptions: Map<String, String>? = emptyMap(),

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),

    @Column(name = "region")
    var regionId: Int?,

    @OneToMany
    @JoinColumn(name = "pokedex_id", referencedColumnName = "id")
    var entries: Set<PokedexPokemon> = emptySet(),
)

