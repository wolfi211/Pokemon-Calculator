@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.utilities

import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.ExternalLanguage
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResourceList
import java.net.URI
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
interface UtilityApi {

    @GetExchange(PATH_LANGUAGE)
    fun getAllLanguages(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_LANGUAGE/{id}")
    fun getLanguageById(@PathVariable id: Int): ExternalLanguage

    @GetExchange
    fun followLanguage(url: URI): ExternalLanguage

    companion object {
        const val PATH_LANGUAGE = "/language"
    }
}
