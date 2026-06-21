package hu.danielwolf.pokecounter.config.api

actual fun createApiProperties(): ApiProperties =
  ApiProperties(baseUrl = "http://10.0.2.2:8080")
