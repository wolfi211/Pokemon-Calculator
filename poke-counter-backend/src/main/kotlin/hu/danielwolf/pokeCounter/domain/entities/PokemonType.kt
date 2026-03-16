package hu.danielwolf.pokeCounter.domain.entities

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
@Table(name = "pokemon_types")
data class PokemonType(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokemon_types_id_seq")
    @SequenceGenerator(name = "pokemon_types_id_seq", sequenceName = "pokemon_types_id_seq", allocationSize = 1)
    var id: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokemon_id", nullable = false)
    var pokemon: Pokemon,

    @Column(name = "slot", nullable = false)
    var slot: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    var type: Type,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generation_id")
    var generation: Generation?,
)
