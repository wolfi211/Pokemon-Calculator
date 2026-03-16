package hu.danielwolf.pokeCounter.external.api.games

import hu.danielwolf.pokeCounter.external.api.games.dto.ExternalGeneration
import hu.danielwolf.pokeCounter.external.api.games.dto.ExternalPokedex
import hu.danielwolf.pokeCounter.external.api.games.dto.ExternalVersion
import hu.danielwolf.pokeCounter.external.api.games.dto.ExternalVersionGroup
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResourceList
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import java.net.URI

@HttpExchange
interface GameApi {

    @GetExchange(PATH_GENERATION)
    fun getAllGenerations(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_GENERATION/{id}")
    fun getGenerationById(@PathVariable id: Int): ExternalGeneration

    @GetExchange
    fun followGeneration(url: URI): ExternalGeneration

    @GetExchange(PATH_POKEDEX)
    fun getAllPokedexes(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_POKEDEX/{id}")
    fun getPokedexById(@PathVariable id: Int): ExternalPokedex

    @GetExchange
    fun followPokedex(url: URI): ExternalPokedex

    @GetExchange(PATH_VERSION)
    fun getAllVersions(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_VERSION/{id}")
    fun getVersionById(@PathVariable id: Int): ExternalVersion

    @GetExchange
    fun followVersion(url: URI): ExternalVersion

    @GetExchange(PATH_VERSION_GROUP)
    fun getAllVersionGroups(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_VERSION_GROUP/{id}")
    fun getVersionGroupById(@PathVariable id: Int): ExternalVersionGroup

    @GetExchange
    fun followVersionGroup(url: URI): ExternalVersionGroup

    companion object {
        const val PATH_GENERATION = "/generation"
        const val PATH_POKEDEX = "/pokedex"
        const val PATH_VERSION = "/version"
        const val PATH_VERSION_GROUP = "/version-group"
    }
}
