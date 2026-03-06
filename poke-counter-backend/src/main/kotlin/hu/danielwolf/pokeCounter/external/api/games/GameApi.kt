package hu.danielwolf.pokeCounter.external.api.games

import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
interface GameApi {

    companion object {
        const val PATH_GENERATION = "/generation"
        const val PATH_POKEDEX = "/pokedex"
        const val PATH_VERSION = "/version"
        const val PATH_VERSION_GROUP = "/version-group"
    }
}