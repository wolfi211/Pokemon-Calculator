package hu.danielwolf.pokecounter.api.counter

import hu.danielwolf.pokecounter.api.counter.dto.PokemonSummaryDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PokeCounterApi(
  private val client: HttpClient,
) {
  suspend fun searchPokemon(
    query: String,
    versionGroup: Int? = null,
  ): List<PokemonSummaryDto> =
    client.get("/api/v1/pokemon/minified-search") {
      parameter("query", query)
      versionGroup?.let { parameter("versionGroup", it) }
    }.body()
}
