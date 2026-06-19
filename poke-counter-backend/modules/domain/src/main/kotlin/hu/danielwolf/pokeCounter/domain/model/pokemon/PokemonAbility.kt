package hu.danielwolf.pokeCounter.domain.model.pokemon

import hu.danielwolf.pokeCounter.domain.model.persistenceHashCode
import hu.danielwolf.pokeCounter.domain.model.games.Generation
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
@Table(name = "pokemon_ability")
data class PokemonAbility(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokemon_ability_id_seq")
    @SequenceGenerator(name = "pokemon_ability_id_seq", sequenceName = "pokemon_ability_id_seq", allocationSize = 1)
    @Column(name = "id")
    var id: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokemon_id")
    var pokemon: Pokemon,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ability_id")
    var ability: Ability? = null,

    @Column(name = "is_hidden")
    var isHidden: Boolean?,

    @Column(name = "slot")
    var slot: Int?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generation_id")
    var generation: Generation?,
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || javaClass != other.javaClass) return false
    other as PokemonAbility
    if (id == 0 || other.id == 0) return false
    return id == other.id
  }

  override fun hashCode(): Int = persistenceHashCode(id)
}

