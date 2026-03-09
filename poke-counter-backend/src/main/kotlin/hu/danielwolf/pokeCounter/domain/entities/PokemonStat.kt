package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "pokemon_stats")
data class PokemonStat(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "pokemon_id")
    var pokemonId: Int,

    @Column(name = "stat_id")
    var statId: Int,

    @Column(name = "generation_id")
    var generationId: Int,

    @Column(name = "base_stat")
    var baseStat: Int,

    @Column(name = "effort")
    var effort: Int,
)

