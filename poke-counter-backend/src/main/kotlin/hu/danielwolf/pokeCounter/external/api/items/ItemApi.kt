package hu.danielwolf.pokeCounter.external.api.items

import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
interface ItemApi {

    companion object {
        const val PATH_ITEM = "/item"
        const val PATH_ITEM_ATTRIBUTE = "/item-attribute"
        const val PATH_ITEM_CATEGORY = "/item-category"
        const val PATH_ITEM_FLINT_EFFECT = "/item-flint-effect"
        const val PATH_ITEM_POCKET = "/item-pocket"
    }
}