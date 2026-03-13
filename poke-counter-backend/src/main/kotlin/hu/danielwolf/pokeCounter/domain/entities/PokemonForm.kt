package hu.danielwolf.pokeCounter.domain.entities

import hu.danielwolf.pokeCounter.config.JsonMapConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "pokemon_forms")
data class PokemonForm(
    @Id
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @Column(name = "order")
    var order: Int?,

    @Column(name = "form_order")
    var formOrder: Int?,

    @Column(name = "is_default")
    var isDefault: Boolean?,

    @Column(name = "is_battle_only")
    var isBattleOnly: Boolean?,

    @Column(name = "is_mega")
    var isMega: Boolean?,

    @Column(name = "form_name")
    var formName: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokemon_id")
    var pokemon: Pokemon?,

    @Column(name = "sprite")
    var sprite: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "version_group")
    var versionGroup: VersionGroup?,

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "formNames", columnDefinition = "jsonb")
    var formNames: Map<String, String>? = emptyMap(),
)

