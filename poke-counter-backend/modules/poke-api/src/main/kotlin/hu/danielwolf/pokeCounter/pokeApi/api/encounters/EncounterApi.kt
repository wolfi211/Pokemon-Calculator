@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.encounters

import hu.danielwolf.pokeCounter.pokeApi.api.encounters.dto.ExternalEncounterCondition
import hu.danielwolf.pokeCounter.pokeApi.api.encounters.dto.ExternalEncounterConditionValue
import hu.danielwolf.pokeCounter.pokeApi.api.encounters.dto.ExternalEncounterMethod
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResourceList
import java.net.URI
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
interface EncounterApi {

    @GetExchange(PATH_ENCOUNTER_METHOD)
    fun getAllEncounterMethods(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_ENCOUNTER_METHOD/{id}")
    fun getEncounterMethodById(@PathVariable id: Int): ExternalEncounterMethod

    @GetExchange
    fun followEncounterMethod(url: URI): ExternalEncounterMethod

    @GetExchange(PATH_ENCOUNTER_CONDITION)
    fun getAllEncounterConditions(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_ENCOUNTER_CONDITION/{id}")
    fun getEncounterConditionById(@PathVariable id: Int): ExternalEncounterCondition

    @GetExchange
    fun followEncounterCondition(url: URI): ExternalEncounterCondition

    @GetExchange(PATH_ENCOUNTER_CONDITION_VALUE)
    fun getAllEncounterConditionValues(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_ENCOUNTER_CONDITION_VALUE/{id}")
    fun getEncounterConditionValueById(@PathVariable id: Int): ExternalEncounterConditionValue

    @GetExchange
    fun followEncounterConditionValue(url: URI): ExternalEncounterConditionValue

    companion object {
        const val PATH_ENCOUNTER_METHOD = "/encounter-method"
        const val PATH_ENCOUNTER_CONDITION = "/encounter-condition"
        const val PATH_ENCOUNTER_CONDITION_VALUE = "/encounter-condition-value"
    }
}