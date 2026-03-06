package hu.danielwolf.pokeCounter.external.api.move

import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
interface MoveApi {

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