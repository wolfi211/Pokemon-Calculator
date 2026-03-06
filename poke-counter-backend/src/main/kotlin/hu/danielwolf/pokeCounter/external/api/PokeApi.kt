package hu.danielwolf.pokeCounter.external.api

import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
interface PokeApi {

  companion object {
    const val PATH_POKEMON = "/pokemon"
  }
}