package hu.danielwolf.pokeCounter.domain.entities

import hu.danielwolf.pokeCounter.config.JsonMapConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "types")
data class Type(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "name", nullable = false, unique = true)
    var name: String,

    @Convert(converter = JsonMapConverter::class)
    @Column(name = "names", columnDefinition = "jsonb")
    var names: Map<String, String>? = emptyMap(),

    @OneToMany
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    var pokemonTypes: Set<PokemonType> = emptySet(),

    @OneToMany
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    var moveTypes: Set<MoveType> = emptySet(),

    @OneToMany
    @JoinColumn(name = "damage_from", referencedColumnName = "id")
    var damageFromRelations: Set<TypeRelation> = emptySet(),

    @OneToMany
    @JoinColumn(name = "damage_to", referencedColumnName = "id")
    var damageToRelations: Set<TypeRelation> = emptySet(),
)

