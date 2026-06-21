package hu.danielwolf.pokecounter.config.api

data class ApiProperties(
  val baseUrl: String,
)

expect fun createApiProperties(): ApiProperties
