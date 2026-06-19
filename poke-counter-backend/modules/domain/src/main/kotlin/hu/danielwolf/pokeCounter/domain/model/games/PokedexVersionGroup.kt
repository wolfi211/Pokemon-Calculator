package hu.danielwolf.pokeCounter.domain.model.games

import hu.danielwolf.pokeCounter.domain.model.persistenceHashCode
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
@Table(name = "pokedex_version_group")
data class PokedexVersionGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokedex_version_group_id_seq")
    @SequenceGenerator(name = "pokedex_version_group_id_seq", sequenceName = "pokedex_version_group_id_seq", allocationSize = 1)
    var id: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokedex_id")
    var pokedex: Pokedex,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "version_group_id")
    var versionGroup: VersionGroup,
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || javaClass != other.javaClass) return false
    other as PokedexVersionGroup
    if (id == 0 || other.id == 0) return false
    return id == other.id
  }

  override fun hashCode(): Int = persistenceHashCode(id)
}

