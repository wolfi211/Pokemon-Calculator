package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "type_relations")
data class TypeRelation(
    @Id
    @Column(name = "id")
    var id: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "damage_from")
    var damageFromType: Type,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "damage_to")
    var damageToType: Type,

    @Column(name = "multiplier")
    var multiplier: BigDecimal,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "generation")
    var generation: Generation?,
)

