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

  @Bean
  fun json() = Json {
    coerceInputValues = true
    encodeDefaults = true
    ignoreUnknownKeys = true
    isLenient = true
    explicitNulls = false
  }

  @Bean
  fun kotlinSerializationConverter(json: Json) =
    KotlinSerializationJsonHttpMessageConverter(json)
}

@OptIn(ExperimentalSerializationApi::class)
fun json(builder: ((JsonBuilder) -> Unit)? = null) = Json {
  namingStrategy = JsonNamingStrategy.SnakeCase
  coerceInputValues = true
  encodeDefaults = true
  ignoreUnknownKeys = true
  isLenient = true
  explicitNulls = false
  builder?.invoke(this)
}