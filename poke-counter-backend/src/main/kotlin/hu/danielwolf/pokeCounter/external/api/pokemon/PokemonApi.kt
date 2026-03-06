package hu.danielwolf.pokeCounter.external.api.pokemon

import org.springframework.web.service.annotation.HttpExchange

@HttpExchange
interface PokemonApi {

    companion object {
        const val PATH_ABILITY = "/ability"
        const val PATH_CHARACTERISTIC = "/characteristic"
        const val PATH_EGG_GROUP = "/egg-group"
        const val PATH_GENDER = "/gender"
        const val PATH_GROWTH_RATE = "/growth-rate"
        const val PATH_NATURE = "/nature"
        const val PATH_POKEATHLON_STAT = "/pokeathlon-stat"
        const val PATH_POKEMON = "/pokemon"
        const val PATH_POKEMON_LOCATION_AREA_ENCOUNTER = "/pokemon/:id/encounters"
        const val PATH_POKEMON_COLOR = "/pokemon-color"
        const val PATH_POKEMON_FORM = "/pokemon-form"
        const val PATH_POKEMON_HABITAT = "/pokemon-habitat"
        const val PATH_POKEMON_SHAPE = "/pokemon-shape"
        const val PATH_POKEMON_SPECIES = "/pokemon-species"
        const val PATH_STAT = "/stat"
        const val PATH_TYPE = "/type"
    }
}