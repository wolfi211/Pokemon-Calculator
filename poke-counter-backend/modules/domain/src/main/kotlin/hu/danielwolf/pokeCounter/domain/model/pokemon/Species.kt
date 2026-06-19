package hu.danielwolf.pokeCounter.domain.model.pokemon

import jakarta.persistence.Column
import jakarta.persistence.Entity
import org.hibernate.annotations.BatchSize
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@BatchSize(size = 32)
@Table(name = "species")
data class Species(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @Column(name = "order")
    var order: Int?,

    @Column(name = "gender_rate")
    var genderRate: Int?,

    @Column(name = "capture_rate")
    var captureRate: Int?,

    @Column(name = "base_happiness")
    var baseHappiness: Int?,

    @Column(name = "is_baby")
    var isBaby: Boolean?,

    @Column(name = "is_legendary")
    var isLegendary: Boolean?,

    @Column(name = "is_mythical")
    var isMythical: Boolean?,

    @Column(name = "hatch_counter")
    var hatchCounter: Int?,

    @Column(name = "has_gender_differences")
    var hasGenderDifferences: Boolean?,

    @Column(name = "forms_switchable")
    var formsSwitchable: Boolean?,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "flavor_text_entries", columnDefinition = "jsonb")
    var flavorTextEntries: Map<String, String>? = emptyMap(),

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "form_descriptions", columnDefinition = "jsonb")
    var formDescriptions: Map<String, String>? = emptyMap(),

    @OneToMany(mappedBy = "species")
    var pokemon: MutableSet<Pokemon> = mutableSetOf(),
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other == null || javaClass != other.javaClass) return false
    return id == (other as Species).id
  }

  override fun hashCode(): Int = id
}

