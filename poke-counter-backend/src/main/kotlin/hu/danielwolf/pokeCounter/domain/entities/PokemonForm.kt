package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "pokemon_forms")
data class PokemonForm(
    @Id
    @Column(name = "id")
    val id: Int,

    @Column(name = "name", nullable = false, unique = true)
    val name: String,

    @Column(name = "order")
    val order: Int?,

    @Column(name = "form_order")
    val formOrder: Int?,

    @Column(name = "is_default")
    val isDefault: Boolean?,

    @Column(name = "is_battle_only")
    val isBattleOnly: Boolean?,

    @Column(name = "is_mega")
    val isMega: Boolean?,

    @Column(name = "form_name")
    val formName: String,

    @Column(name = "pokemon_id")
    val pokemonId: Int?,

    @Column(name = "sprite")
    val sprite: String?,

    @Column(name = "version_group")
    val versionGroupId: Int?,

    @Column(name = "names", columnDefinition = "jsonb")
    val names: Map<String, String>?,

    @Column(name = "formNames", columnDefinition = "jsonb")
    val formNames: Map<String, String>?,
)

