package hu.danielwolf.pokeCounter.external.api.items

import hu.danielwolf.pokeCounter.external.api.items.dto.ExternalItem
import hu.danielwolf.pokeCounter.external.api.items.dto.ExternalItemAttribute
import hu.danielwolf.pokeCounter.external.api.items.dto.ExternalItemCategory
import hu.danielwolf.pokeCounter.external.api.items.dto.ExternalItemFlingEffect
import hu.danielwolf.pokeCounter.external.api.items.dto.ExternalItemPocket
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedAPIResourceList
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import java.net.URI

@HttpExchange
interface ItemApi {

    @GetExchange(PATH_ITEM)
    fun getAllItems(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_ITEM/{id}")
    fun getItemById(@PathVariable id: Int): ExternalItem

    @GetExchange
    fun followItem(url: URI): ExternalItem

    @GetExchange(PATH_ITEM_ATTRIBUTE)
    fun getAllItemAttributes(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_ITEM_ATTRIBUTE/{id}")
    fun getItemAttributeById(@PathVariable id: Int): ExternalItemAttribute

    @GetExchange
    fun followItemAttribute(url: URI): ExternalItemAttribute

    @GetExchange(PATH_ITEM_CATEGORY)
    fun getAllItemCategories(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_ITEM_CATEGORY/{id}")
    fun getItemCategoryById(@PathVariable id: Int): ExternalItemCategory

    @GetExchange
    fun followItemCategory(url: URI): ExternalItemCategory

    @GetExchange(PATH_ITEM_FLING_EFFECT)
    fun getAllItemFlingEffects(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_ITEM_FLING_EFFECT/{id}")
    fun getItemFlingEffectById(@PathVariable id: Int): ExternalItemFlingEffect

    @GetExchange
    fun followItemFlingEffect(url: URI): ExternalItemFlingEffect

    @GetExchange(PATH_ITEM_POCKET)
    fun getAllItemPockets(@RequestParam("offset") offset: Int = 0, @RequestParam("limit") limit: Int = 20): NamedAPIResourceList

    @GetExchange("$PATH_ITEM_POCKET/{id}")
    fun getItemPocketById(@PathVariable id: Int): ExternalItemPocket

    @GetExchange
    fun followItemPocket(url: URI): ExternalItemPocket

    companion object {
        const val PATH_ITEM = "/item"
        const val PATH_ITEM_ATTRIBUTE = "/item-attribute"
        const val PATH_ITEM_CATEGORY = "/item-category"
        const val PATH_ITEM_FLING_EFFECT = "/item-fling-effect"
        const val PATH_ITEM_POCKET = "/item-pocket"
    }
}
