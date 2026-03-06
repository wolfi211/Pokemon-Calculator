package hu.danielwolf.pokeCounter.external.api.locations

import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
interface LocationApi {

    companion object {
        const val PATH_LOCATION = "/location"
        const val PATH_LOCATION_AREA = "/location-area"
        const val PATH_PAL_PARK_AREA = "/pal-park-area"
        const val PATH_REGION = "/region"
    }
}