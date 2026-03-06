package hu.danielwolf.pokeCounter.external.api.evolution

import hu.danielwolf.pokeCounter.external.api.evolution.dto.ExternalEvolutionChain
import hu.danielwolf.pokeCounter.external.api.evolution.dto.ExternalEvolutionTrigger
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedApiResourceList
import hu.danielwolf.pokeCounter.external.api.utilities.dto.PageRequest
import hu.danielwolf.pokeCounter.external.api.utilities.dto.UnnamedApiResourceList
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import java.net.URI

@HttpExchange
interface EvolutionApi {

    @GetExchange(PATH_EVOLUTION_CHAIN)
    fun getAllEvolutionChains(@RequestParam pager: PageRequest): UnnamedApiResourceList

    @GetExchange("$PATH_EVOLUTION_CHAIN/{id}")
    fun getEvolutionChainById(@PathVariable id: Int): ExternalEvolutionChain

    @GetExchange
    fun followEvolutionChain(url: URI): ExternalEvolutionChain

    @GetExchange(PATH_EVOLUTION_TRIGGER)
    fun getAllEvolutionTriggers(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_EVOLUTION_TRIGGER/{id}")
    fun getEvolutionTriggerById(@PathVariable id: Int): ExternalEvolutionTrigger

    @GetExchange
    fun followEvolutionTrigger(url: URI): ExternalEvolutionTrigger

    companion object {
        const val PATH_EVOLUTION_CHAIN = "/evolution-chain"
        const val PATH_EVOLUTION_TRIGGER = "/evolution-trigger"
    }
}
