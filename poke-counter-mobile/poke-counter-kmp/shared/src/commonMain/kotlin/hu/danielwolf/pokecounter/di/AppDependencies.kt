package hu.danielwolf.pokecounter.di

import hu.danielwolf.pokecounter.api.counter.PokeCounterApi
import hu.danielwolf.pokecounter.config.api.ApiConfig
import hu.danielwolf.pokecounter.config.api.createApiProperties

object AppDependencies {
  private val apiProperties = createApiProperties()

  val httpClient = ApiConfig.createHttpClient(apiProperties)

  val pokeCounterApi = PokeCounterApi(httpClient)
}
