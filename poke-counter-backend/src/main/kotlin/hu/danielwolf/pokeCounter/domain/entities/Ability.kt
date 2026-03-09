package hu.danielwolf.pokeCounter.domain.entities

import hu.danielwolf.pokeCounter.config.JsonMapConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

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

    @Column(name = "generation")
    var generationId: Int?,

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "flavor_texts", columnDefinition = "jsonb")
    var flavorTexts: Map<String, String>? = emptyMap(),
)

