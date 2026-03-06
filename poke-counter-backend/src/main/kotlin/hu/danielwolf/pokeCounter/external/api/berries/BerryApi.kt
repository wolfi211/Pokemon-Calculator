package hu.danielwolf.pokeCounter.external.api.berries

import hu.danielwolf.pokeCounter.external.api.berries.dto.ExternalBerry
import hu.danielwolf.pokeCounter.external.api.berries.dto.ExternalBerryFirmness
import hu.danielwolf.pokeCounter.external.api.berries.dto.ExternalBerryFlavor
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedApiResourceList
import hu.danielwolf.pokeCounter.external.api.utilities.dto.PageRequest
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import java.net.URI

@HttpExchange
interface BerryApi {

    @GetExchange(PATH_BERRY)
    fun getAllBerries(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_BERRY/{id}")
    fun getBerryById(@PathVariable id: Int): ExternalBerry

    @GetExchange
    fun followBerry(url: URI): ExternalBerry

    @GetExchange(PATH_BERRY_FIRMNESS)
    fun getAllBerryFirmness(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_BERRY_FIRMNESS/{id}")
    fun getBerryFirmnessById(@PathVariable id: Int): ExternalBerryFirmness

    @GetExchange
    fun followBerryFirmness(url: URI): ExternalBerryFirmness

    @GetExchange(PATH_BERRY_FLAVOR)
    fun getAllBerryFlavors(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_BERRY_FLAVOR/{id}")
    fun getBerryFlavorById(@PathVariable id: Int): ExternalBerryFlavor

    @GetExchange
    fun followBerryFlavor(url: URI): ExternalBerryFlavor

    companion object {
        const val PATH_BERRY = "/berry"
        const val PATH_BERRY_FIRMNESS = "/berry-firmness"
        const val PATH_BERRY_FLAVOR = "/berry-flavor"
    }
}