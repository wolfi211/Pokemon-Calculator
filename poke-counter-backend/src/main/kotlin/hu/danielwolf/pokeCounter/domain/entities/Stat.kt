package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(name = "stats")
data class Stat(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),

    @OneToMany(mappedBy = "stat")
    var pokemonStats: MutableSet<PokemonStat> = mutableSetOf(),
)

