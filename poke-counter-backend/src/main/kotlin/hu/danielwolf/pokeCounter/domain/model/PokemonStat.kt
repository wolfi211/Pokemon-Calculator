package hu.danielwolf.pokeCounter.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table

@Entity
@Table(name = "pokemon_stats")
data class PokemonStat(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokemon_stats_id_seq")
    @SequenceGenerator(name = "pokemon_stats_id_seq", sequenceName = "pokemon_stats_id_seq", allocationSize = 1)
    @Column(name = "id")
    var id: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokemon_id")
    var pokemon: Pokemon,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stat_id")
    var stat: Stat,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generation_id")
    var generation: Generation?,

    @Column(name = "base_stat")
    var baseStat: Int,

    @Column(name = "effort")
    var effort: Int,
)

