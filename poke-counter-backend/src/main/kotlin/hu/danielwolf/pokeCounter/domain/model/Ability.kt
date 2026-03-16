package hu.danielwolf.pokeCounter.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(name = "ability")
data class Ability(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @Column(name = "is_main_series")
    var isMainSeries: Boolean?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generation")
    var generation: Generation?,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "flavor_texts", columnDefinition = "jsonb")
    var flavorTexts: Map<String, String>? = emptyMap(),
)

