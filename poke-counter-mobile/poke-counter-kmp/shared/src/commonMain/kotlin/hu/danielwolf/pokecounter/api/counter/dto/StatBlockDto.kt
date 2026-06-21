package hu.danielwolf.pokecounter.api.counter.dto

import kotlinx.serialization.Serializable

@Serializable
data class StatBlockDto(
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val speed: Int,
)