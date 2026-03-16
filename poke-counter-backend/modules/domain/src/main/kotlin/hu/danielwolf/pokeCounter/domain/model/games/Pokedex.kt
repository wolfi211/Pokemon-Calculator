package hu.danielwolf.pokeCounter.domain.model.games

import hu.danielwolf.pokeCounter.domain.model.locations.Region
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokedexPokemon
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
@Table(name = "pokedexes")
data class Pokedex(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @Column(name = "is_main_series")
    var isMainSeries: Boolean?,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "descriptions", columnDefinition = "jsonb")
    var descriptions: Map<String, String>? = emptyMap(),

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region")
    var region: Region?,

    @OneToMany(mappedBy = "pokedex")
    var entries: MutableSet<PokedexPokemon> = mutableSetOf(),
)

