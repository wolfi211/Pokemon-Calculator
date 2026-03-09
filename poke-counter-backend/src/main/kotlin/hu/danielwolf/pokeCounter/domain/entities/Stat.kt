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
@Table(name = "stats")
data class Stat(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),

    @OneToMany
    @JoinColumn(name = "stat_id", referencedColumnName = "id")
    var pokemonStats: Set<PokemonStat> = emptySet(),
)

