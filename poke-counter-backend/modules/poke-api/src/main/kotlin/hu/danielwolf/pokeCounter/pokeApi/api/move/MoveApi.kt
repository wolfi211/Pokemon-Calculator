@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.api.move

import hu.danielwolf.pokeCounter.pokeApi.api.move.dto.ExternalLearnMethod
import hu.danielwolf.pokeCounter.pokeApi.api.move.dto.ExternalMove
import hu.danielwolf.pokeCounter.pokeApi.api.move.dto.ExternalMoveAilment
import hu.danielwolf.pokeCounter.pokeApi.api.move.dto.ExternalMoveBattleStyle
import hu.danielwolf.pokeCounter.pokeApi.api.move.dto.ExternalMoveCategory
import hu.danielwolf.pokeCounter.pokeApi.api.move.dto.ExternalMoveDamageClass
import hu.danielwolf.pokeCounter.pokeApi.api.move.dto.ExternalMoveTarget
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.dto.NamedAPIResourceList
import java.net.URI
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
interface MoveApi {

    @GetExchange(PATH_MOVE)
    fun getAllMoves(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_MOVE/{id}")
    fun getMoveById(@PathVariable id: Int): ExternalMove

    @GetExchange
    fun followMove(url: URI): ExternalMove

    @GetExchange(PATH_MOVE_AILMENT)
    fun getAllMoveAilments(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_MOVE_AILMENT/{id}")
    fun getMoveAilmentById(@PathVariable id: Int): ExternalMoveAilment

    @GetExchange
    fun followMoveAilment(url: URI): ExternalMoveAilment

    @GetExchange(PATH_MOVE_BATTLE_STYLE)
    fun getAllMoveBattleStyles(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_MOVE_BATTLE_STYLE/{id}")
    fun getMoveBattleStyleById(@PathVariable id: Int): ExternalMoveBattleStyle

    @GetExchange
    fun followMoveBattleStyle(url: URI): ExternalMoveBattleStyle

    @GetExchange(PATH_MOVE_CATEGORY)
    fun getAllMoveCategories(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_MOVE_CATEGORY/{id}")
    fun getMoveCategoryById(@PathVariable id: Int): ExternalMoveCategory

    @GetExchange
    fun followMoveCategory(url: URI): ExternalMoveCategory

    @GetExchange(PATH_MOVE_DAMAGE_CLASS)
    fun getAllMoveDamageClasses(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_MOVE_DAMAGE_CLASS/{id}")
    fun getMoveDamageClassById(@PathVariable id: Int): ExternalMoveDamageClass

    @GetExchange
    fun followMoveDamageClass(url: URI): ExternalMoveDamageClass

    @GetExchange(PATH_MOVE_LEARN_METHOD)
    fun getAllMoveLearnMethods(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_MOVE_LEARN_METHOD/{id}")
    fun getMoveLearnMethodById(@PathVariable id: Int): ExternalLearnMethod

    @GetExchange
    fun followMoveLearnMethod(url: URI): ExternalLearnMethod

    @GetExchange(PATH_MOVE_TARGET)
    fun getAllMoveTargets(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_MOVE_TARGET/{id}")
    fun getMoveTargetById(@PathVariable id: Int): ExternalMoveTarget

    @GetExchange
    fun followMoveTarget(url: URI): ExternalMoveTarget

    companion object {
        const val PATH_MOVE = "/move"
        const val PATH_MOVE_AILMENT = "/move-ailment"
        const val PATH_MOVE_BATTLE_STYLE = "/move-battle-style"
        const val PATH_MOVE_CATEGORY = "/move-category"
        const val PATH_MOVE_DAMAGE_CLASS = "/move-damage-class"
        const val PATH_MOVE_LEARN_METHOD = "/move-learn-method"
        const val PATH_MOVE_TARGET = "/move-target"
    }
}
