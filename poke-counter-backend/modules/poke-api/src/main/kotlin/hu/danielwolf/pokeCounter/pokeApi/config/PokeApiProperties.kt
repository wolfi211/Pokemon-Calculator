@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

@Validated
@ConfigurationProperties("poke-api")
data class PokeApiProperties(
  val baseUrl: String,
  val minDelayBetweenRequestsMs: Long = 100L,
)