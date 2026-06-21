package hu.danielwolf.pokecounter.api.counter.dto

import kotlinx.serialization.Serializable

@Serializable
data class MinifiedTypeDto(
    val id: Int,
    val slot: Int?,
    val name: String,
    val localizedName: String,
)