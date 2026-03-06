package hu.danielwolf.pokeCounter.external.api.utilities

import hu.danielwolf.pokeCounter.external.api.utilities.dto.ExternalLanguage
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedApiResourceList
import hu.danielwolf.pokeCounter.external.api.utilities.dto.PageRequest
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import java.net.URI

@HttpExchange
interface UtilityApi {

    @GetExchange(PATH_LANGUAGE)
    fun getAllLanguages(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_LANGUAGE/{id}")
    fun getLanguageById(@PathVariable id: Int): ExternalLanguage

    @GetExchange
    fun followLanguage(url: URI): ExternalLanguage

    companion object {
        const val PATH_LANGUAGE = "/language"
    }
}
