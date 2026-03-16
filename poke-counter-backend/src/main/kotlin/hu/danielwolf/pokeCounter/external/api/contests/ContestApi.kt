package hu.danielwolf.pokeCounter.external.api.contests

import hu.danielwolf.pokeCounter.external.api.contests.dto.ExternalContestEffect
import hu.danielwolf.pokeCounter.external.api.contests.dto.ExternalContestType
import hu.danielwolf.pokeCounter.external.api.contests.dto.ExternalSuperContestEffect
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResourceList
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import java.net.URI

@HttpExchange
interface ContestApi {

    @GetExchange(PATH_CONTEST_TYPE)
    fun getAllContestTypes(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_CONTEST_TYPE/{id}")
    fun getContestTypeById(@PathVariable id: Int): ExternalContestType

    @GetExchange
    fun followContestType(url: URI): ExternalContestType

    @GetExchange(PATH_CONTEST_EFFECT)
    fun getAllContestEffects(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_CONTEST_EFFECT/{id}")
    fun getContestEffectById(@PathVariable id: Int): ExternalContestEffect

    @GetExchange
    fun followContestEffect(url: URI): ExternalContestEffect

    @GetExchange(PATH_SUPER_CONTEST_EFFECT)
    fun getAllSuperContestEffects(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_SUPER_CONTEST_EFFECT/{id}")
    fun getSuperContestEffectById(@PathVariable id: Int): ExternalSuperContestEffect

    @GetExchange
    fun followSuperContestEffect(url: URI): ExternalSuperContestEffect

    companion object {
        const val PATH_CONTEST_TYPE = "/contest-type"
        const val PATH_CONTEST_EFFECT = "/contest-effect"
        const val PATH_SUPER_CONTEST_EFFECT = "super-contest-effect"
    }
}