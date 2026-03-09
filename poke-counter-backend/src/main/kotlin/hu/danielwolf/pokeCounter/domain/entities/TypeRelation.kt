package hu.danielwolf.pokeCounter.domain.entities

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
    var id: Int,

    @Column(name = "damage_from")
    var damageFromTypeId: Int,

    @Column(name = "damage_to")
    var damageToTypeId: Int,

    @Column(name = "multiplier")
    var multiplier: BigDecimal,

    @Column(name = "generation")
    var generationId: Int?,
)

