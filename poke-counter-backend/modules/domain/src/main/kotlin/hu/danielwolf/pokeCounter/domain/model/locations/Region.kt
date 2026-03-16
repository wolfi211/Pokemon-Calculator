package hu.danielwolf.pokeCounter.domain.model.locations

import hu.danielwolf.pokeCounter.domain.model.games.Generation
import hu.danielwolf.pokeCounter.domain.model.games.Pokedex
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(name = "regions")
data class Region(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_generation")
    var mainGeneration: Generation? = null,

    @OneToMany(mappedBy = "region")
    var pokedexes: MutableSet<Pokedex> = mutableSetOf(),
)
