package hu.danielwolf.pokeCounter.external.api.encounters

import hu.danielwolf.pokeCounter.external.api.encounters.dto.ExternalEncounterCondition
import hu.danielwolf.pokeCounter.external.api.encounters.dto.ExternalEncounterConditionValue
import hu.danielwolf.pokeCounter.external.api.encounters.dto.ExternalEncounterMethod
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedApiResourceList
import hu.danielwolf.pokeCounter.external.api.utilities.dto.PageRequest
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import java.net.URI

@HttpExchange
interface EncounterApi {

    @GetExchange(PATH_ENCOUNTER_METHOD)
    fun getAllEncounterMethods(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_ENCOUNTER_METHOD/{id}")
    fun getEncounterMethodById(@PathVariable id: Int): ExternalEncounterMethod

    @GetExchange
    fun followEncounterMethod(url: URI): ExternalEncounterMethod

    @GetExchange(PATH_ENCOUNTER_CONDITION)
    fun getAllEncounterConditions(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_ENCOUNTER_CONDITION/{id}")
    fun getEncounterConditionById(@PathVariable id: Int): ExternalEncounterCondition

    @GetExchange
    fun followEncounterCondition(url: URI): ExternalEncounterCondition

    @GetExchange(PATH_ENCOUNTER_CONDITION_VALUE)
    fun getAllEncounterConditionValues(@RequestParam pager: PageRequest): NamedApiResourceList

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