package hu.danielwolf.pokecounter.config.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object ApiConfig {
  fun createHttpClient(properties: ApiProperties): HttpClient =
    HttpClient {
      expectSuccess = true

      defaultRequest {
        url(properties.baseUrl)
        contentType(ContentType.Application.Json)
      }

      install(ContentNegotiation) {
        json(
          Json {
            ignoreUnknownKeys = true
            isLenient = true
          },
        )
      }

      install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.INFO
      }
    }
}
