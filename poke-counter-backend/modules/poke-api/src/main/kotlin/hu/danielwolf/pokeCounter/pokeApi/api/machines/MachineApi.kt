@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.machines

import hu.danielwolf.pokeCounter.pokeApi.api.machines.dto.ExternalMachine
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.UnnamedAPIResourceList
import java.net.URI
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
interface MachineApi {

    @GetExchange(PATH_MACHINE)
    fun getAllMachines(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): UnnamedAPIResourceList

    @GetExchange("$PATH_MACHINE/{id}")
    fun getMachineById(@PathVariable id: Int): ExternalMachine

    @GetExchange
    fun followMachine(url: URI): ExternalMachine

    companion object {
        const val PATH_MACHINE = "/machine"
    }
}
