package hu.danielwolf.pokeCounter.external.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated

@Validated
@ConfigurationProperties("poke-api")
data class PokeApiProperties (
  val baseUrl: String
)