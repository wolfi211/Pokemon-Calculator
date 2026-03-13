package hu.danielwolf.pokeCounter.domain.entities

import hu.danielwolf.pokeCounter.config.JsonMapConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "regions")
data class Region(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "main_generation")
    var mainGeneration: Generation,

    @OneToMany
    @JoinColumn(name = "region", referencedColumnName = "id")
    var pokedexes: Set<Pokedex> = emptySet(),
)

