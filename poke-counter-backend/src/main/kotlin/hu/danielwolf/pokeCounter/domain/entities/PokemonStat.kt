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
    val id: Int,

    @Column(name = "pokemon_id")
    val pokemonId: Int,

    @Column(name = "stat_id")
    val statId: Int,

    @Column(name = "generation_id")
    val generationId: Int,

    @Column(name = "base_stat")
    val baseStat: Int,

    @Column(name = "effort")
    val effort: Int,
)

