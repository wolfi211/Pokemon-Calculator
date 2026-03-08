package hu.danielwolf.pokeCounter.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class LocalizedName(
    val language: String,
    val name: String,
)
