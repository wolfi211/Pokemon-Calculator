package hu.danielwolf.pokeCounter.external.api.machines

import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
interface MachineApi {

    companion object {
        const val PATH_MACHINE = "/machine"
    }
}