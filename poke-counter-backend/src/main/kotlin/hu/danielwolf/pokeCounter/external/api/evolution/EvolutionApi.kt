package hu.danielwolf.pokeCounter.external.api.evolution

import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
interface EvolutionApi {

    companion object {
        const val PATH_EVOLUTION_CHAIN = "/evolution-chain"
        const val PATH_EVOLUTION_TRIGGER = "/evolution-trigger"
    }
}