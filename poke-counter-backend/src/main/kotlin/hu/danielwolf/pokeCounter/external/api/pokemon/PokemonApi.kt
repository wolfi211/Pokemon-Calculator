package hu.danielwolf.pokeCounter.external.api.pokemon

import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalAbility
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalCharacteristic
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalEggGroup
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalGender
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalGrowthRate
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalLocationAreaEncounter
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalNature
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalPokeathlonStat
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalPokemon
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalPokemonColor
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalPokemonForm
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalPokemonHabitat
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalPokemonShape
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalPokemonSpecies
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalStat
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalType
import hu.danielwolf.pokeCounter.external.api.utilities.dto.NamedApiResourceList
import hu.danielwolf.pokeCounter.external.api.utilities.dto.PageRequest
import hu.danielwolf.pokeCounter.external.api.utilities.dto.UnnamedApiResourceList
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange
import java.net.URI

@HttpExchange
interface PokemonApi {

    @GetExchange(PATH_ABILITY)
    fun getAllAbilities(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_ABILITY/{id}")
    fun getAbilityById(@PathVariable id: Int): ExternalAbility

    @GetExchange
    fun followAbility(url: URI): ExternalAbility

    @GetExchange(PATH_CHARACTERISTIC)
    fun getAllCharacteristics(@RequestParam pager: PageRequest): UnnamedApiResourceList

    @GetExchange("$PATH_CHARACTERISTIC/{id}")
    fun getCharacteristicById(@PathVariable id: Int): ExternalCharacteristic

    @GetExchange
    fun followCharacteristic(url: URI): ExternalCharacteristic

    @GetExchange(PATH_EGG_GROUP)
    fun getAllEggGroups(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_EGG_GROUP/{id}")
    fun getEggGroupById(@PathVariable id: Int): ExternalEggGroup

    @GetExchange
    fun followEggGroup(url: URI): ExternalEggGroup

    @GetExchange(PATH_GENDER)
    fun getAllGenders(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_GENDER/{id}")
    fun getGenderById(@PathVariable id: Int): ExternalGender

    @GetExchange
    fun followGender(url: URI): ExternalGender

    @GetExchange(PATH_GROWTH_RATE)
    fun getAllGrowthRates(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_GROWTH_RATE/{id}")
    fun getGrowthRateById(@PathVariable id: Int): ExternalGrowthRate

    @GetExchange
    fun followGrowthRate(url: URI): ExternalGrowthRate

    @GetExchange(PATH_NATURE)
    fun getAllNatures(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_NATURE/{id}")
    fun getNatureById(@PathVariable id: Int): ExternalNature

    @GetExchange
    fun followNature(url: URI): ExternalNature

    @GetExchange(PATH_POKEATHLON_STAT)
    fun getAllPokeathlonStats(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_POKEATHLON_STAT/{id}")
    fun getPokeathlonStatById(@PathVariable id: Int): ExternalPokeathlonStat

    @GetExchange
    fun followPokeathlonStat(url: URI): ExternalPokeathlonStat

    @GetExchange(PATH_POKEMON)
    fun getAllPokemon(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_POKEMON/{id}")
    fun getPokemonById(@PathVariable id: Int): ExternalPokemon

    @GetExchange
    fun followPokemon(url: URI): ExternalPokemon

    @GetExchange("$PATH_POKEMON/{pokemonId}$PATH_ENCOUNTER")
    fun getPokemonLocationAreaEncounters(@PathVariable pokemonId: Int): List<ExternalLocationAreaEncounter>

    @GetExchange
    fun followPokemonLocationAreaEncounters(url: URI): List<ExternalLocationAreaEncounter>

    @GetExchange(PATH_POKEMON_COLOR)
    fun getAllPokemonColors(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_POKEMON_COLOR/{id}")
    fun getPokemonColorById(@PathVariable id: Int): ExternalPokemonColor

    @GetExchange
    fun followPokemonColor(url: URI): ExternalPokemonColor

    @GetExchange(PATH_POKEMON_FORM)
    fun getAllPokemonForms(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_POKEMON_FORM/{id}")
    fun getPokemonFormById(@PathVariable id: Int): ExternalPokemonForm

    @GetExchange
    fun followPokemonForm(url: URI): ExternalPokemonForm

    @GetExchange(PATH_POKEMON_HABITAT)
    fun getAllPokemonHabitats(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_POKEMON_HABITAT/{id}")
    fun getPokemonHabitatById(@PathVariable id: Int): ExternalPokemonHabitat

    @GetExchange
    fun followPokemonHabitat(url: URI): ExternalPokemonHabitat

    @GetExchange(PATH_POKEMON_SHAPE)
    fun getAllPokemonShapes(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_POKEMON_SHAPE/{id}")
    fun getPokemonShapeById(@PathVariable id: Int): ExternalPokemonShape

    @GetExchange
    fun followPokemonShape(url: URI): ExternalPokemonShape

    @GetExchange(PATH_POKEMON_SPECIES)
    fun getAllPokemonSpecies(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_POKEMON_SPECIES/{id}")
    fun getPokemonSpeciesById(@PathVariable id: Int): ExternalPokemonSpecies

    @GetExchange
    fun followPokemonSpecies(url: URI): ExternalPokemonSpecies

    @GetExchange(PATH_STAT)
    fun getAllStats(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_STAT/{id}")
    fun getStatById(@PathVariable id: Int): ExternalStat

    @GetExchange
    fun followStat(url: URI): ExternalStat

    @GetExchange(PATH_TYPE)
    fun getAllTypes(@RequestParam pager: PageRequest): NamedApiResourceList

    @GetExchange("$PATH_TYPE/{id}")
    fun getTypeById(@PathVariable id: Int): ExternalType

    @GetExchange
    fun followType(url: URI): ExternalType

    companion object {
        const val PATH_ABILITY = "/ability"
        const val PATH_CHARACTERISTIC = "/characteristic"
        const val PATH_EGG_GROUP = "/egg-group"
        const val PATH_GENDER = "/gender"
        const val PATH_GROWTH_RATE = "/growth-rate"
        const val PATH_NATURE = "/nature"
        const val PATH_POKEATHLON_STAT = "/pokeathlon-stat"
        const val PATH_POKEMON = "/pokemon"
        const val PATH_ENCOUNTER = "/encounters"
        const val PATH_POKEMON_COLOR = "/pokemon-color"
        const val PATH_POKEMON_FORM = "/pokemon-form"
        const val PATH_POKEMON_HABITAT = "/pokemon-habitat"
        const val PATH_POKEMON_SHAPE = "/pokemon-shape"
        const val PATH_POKEMON_SPECIES = "/pokemon-species"
        const val PATH_STAT = "/stat"
        const val PATH_TYPE = "/type"
    }
}
