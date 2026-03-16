@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.evolution

import hu.danielwolf.pokeCounter.pokeApi.api.evolution.dto.ExternalEvolutionChain
import hu.danielwolf.pokeCounter.pokeApi.api.evolution.dto.ExternalEvolutionTrigger
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResourceList
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.UnnamedAPIResourceList
import java.net.URI
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
interface EvolutionApi {

    @GetExchange(PATH_EVOLUTION_CHAIN)
    fun getAllEvolutionChains(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): UnnamedAPIResourceList

    @GetExchange("$PATH_EVOLUTION_CHAIN/{id}")
    fun getEvolutionChainById(@PathVariable id: Int): ExternalEvolutionChain

    @GetExchange
    fun followEvolutionChain(url: URI): ExternalEvolutionChain

    @GetExchange(PATH_EVOLUTION_TRIGGER)
    fun getAllEvolutionTriggers(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_EVOLUTION_TRIGGER/{id}")
    fun getEvolutionTriggerById(@PathVariable id: Int): ExternalEvolutionTrigger

    @GetExchange
    fun followEvolutionTrigger(url: URI): ExternalEvolutionTrigger

    companion object {
        const val PATH_EVOLUTION_CHAIN = "/evolution-chain"
        const val PATH_EVOLUTION_TRIGGER = "/evolution-trigger"
    }
}
