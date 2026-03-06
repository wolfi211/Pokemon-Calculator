package hu.danielwolf.pokeCounter.external.config

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import kotlinx.serialization.json.JsonNamingStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.KotlinSerializationJsonHttpMessageConverter

@Configuration
class SerializationConfig {

  @OptIn(ExperimentalSerializationApi::class)
  @Bean
  fun json(): Json = Json {
    namingStrategy = JsonNamingStrategy.SnakeCase
    ignoreUnknownKeys = true

    coerceInputValues = true
    encodeDefaults = true
    isLenient = true
    explicitNulls = false
  }

  @Bean
  fun kotlinSerializationConverter(json: Json) =
    KotlinSerializationJsonHttpMessageConverter(json)
}