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
    val id: Int,

    @Column(name = "pokemon_id")
    val pokemonId: Int,

    @Column(name = "ability_id")
    val abilityId: Int,

    @Column(name = "is_hidden")
    val isHidden: Boolean?,

    @Column(name = "slot")
    val slot: Int?,

    @Column(name = "generation_id")
    val generationId: Int?,
)

