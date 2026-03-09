package hu.danielwolf.pokeCounter.domain.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "pokemon_ability")
data class PokemonAbility(
    @Id
    @Column(name = "id")
    var id: Int,

    @Column(name = "pokemon_id")
    var pokemonId: Int,

    @Column(name = "ability_id")
    var abilityId: Int,

    @Column(name = "is_hidden")
    var isHidden: Boolean?,

    @Column(name = "slot")
    var slot: Int?,

    @Column(name = "generation_id")
    var generationId: Int?,
)

