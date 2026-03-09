package hu.danielwolf.pokeCounter.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "type_relations")
data class TypeRelation(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "damage_from")
    val damageFromTypeId: Int,

    @Column(name = "damage_to")
    val damageToTypeId: Int,

    @Column(name = "multiplier")
    val multiplier: BigDecimal,

    @Column(name = "generation")
    val generationId: Int?,
)

