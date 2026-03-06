package hu.danielwolf.pokeCounter.external.api.machines

import hu.danielwolf.pokeCounter.external.api.machines.dto.ExternalMachine
import hu.danielwolf.pokeCounter.external.api.utilities.dto.PageRequest
import hu.danielwolf.pokeCounter.external.api.utilities.dto.UnnamedApiResourceList
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import java.net.URI

@HttpExchange
interface MachineApi {

    @GetExchange(PATH_MACHINE)
    fun getAllMachines(@RequestParam pager: PageRequest): UnnamedApiResourceList

    @GetExchange("$PATH_MACHINE/{id}")
    fun getMachineById(@PathVariable id: Int): ExternalMachine

    @GetExchange
    fun followMachine(url: URI): ExternalMachine

    companion object {
        const val PATH_MACHINE = "/machine"
    }
}
