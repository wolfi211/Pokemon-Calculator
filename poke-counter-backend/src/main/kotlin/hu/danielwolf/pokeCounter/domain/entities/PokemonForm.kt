package hu.danielwolf.pokeCounter.domain.entities

import hu.danielwolf.pokeCounter.config.JsonMapConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "pokemon_forms")
data class PokemonForm(
    @Id
    @Column(name = "id")
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

    @Column(name = "pokemon_id")
    var pokemonId: Int?,

    @Column(name = "sprite")
    var sprite: String?,

    @Column(name = "version_group")
    var versionGroupId: Int?,

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "formNames", columnDefinition = "jsonb")
    var formNames: Map<String, String>? = emptyMap(),
)

