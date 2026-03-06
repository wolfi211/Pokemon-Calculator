package hu.danielwolf.pokeCounter.external.api.locations

import hu.danielwolf.pokeCounter.external.api.locations.dto.ExternalLocation
import hu.danielwolf.pokeCounter.external.api.locations.dto.ExternalLocationArea
import hu.danielwolf.pokeCounter.external.api.locations.dto.ExternalPalParkArea
import hu.danielwolf.pokeCounter.external.api.locations.dto.ExternalRegion
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedApiResourceList
import hu.danielwolf.pokeCounter.external.api.utilities.dto.PageRequest
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import java.net.URI

@HttpExchange
interface LocationApi {

    @GetExchange(PATH_LOCATION)
    fun getAllLocations(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_LOCATION/{id}")
    fun getLocationById(@PathVariable id: Int): ExternalLocation

    @GetExchange
    fun followLocation(url: URI): ExternalLocation

    @GetExchange(PATH_LOCATION_AREA)
    fun getAllLocationAreas(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_LOCATION_AREA/{id}")
    fun getLocationAreaById(@PathVariable id: Int): ExternalLocationArea

    @GetExchange
    fun followLocationArea(url: URI): ExternalLocationArea

    @GetExchange(PATH_PAL_PARK_AREA)
    fun getAllPalParkAreas(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_PAL_PARK_AREA/{id}")
    fun getPalParkAreaById(@PathVariable id: Int): ExternalPalParkArea

    @GetExchange
    fun followPalParkArea(url: URI): ExternalPalParkArea

    @GetExchange(PATH_REGION)
    fun getAllRegions(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_REGION/{id}")
    fun getRegionById(@PathVariable id: Int): ExternalRegion

    @GetExchange
    fun followRegion(url: URI): ExternalRegion

    companion object {
        const val PATH_LOCATION = "/location"
        const val PATH_LOCATION_AREA = "/location-area"
        const val PATH_PAL_PARK_AREA = "/pal-park-area"
        const val PATH_REGION = "/region"
    }
}
