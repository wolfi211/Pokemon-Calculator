package hu.danielwolf.pokeCounter.external.api.utilities

import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
interface UtilityApi {

    companion object {
        const val PATH_LANGUAGE = "/language"
    }
}