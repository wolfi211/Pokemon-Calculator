package hu.danielwolf.pokeCounter.external.service.pokemon

import hu.danielwolf.pokeCounter.domain.entities.Pokemon
import hu.danielwolf.pokeCounter.domain.entities.PokemonForm
import hu.danielwolf.pokeCounter.domain.entities.VersionGroup
import hu.danielwolf.pokeCounter.domain.services.PokemonFormService
import hu.danielwolf.pokeCounter.domain.services.PokemonService
import hu.danielwolf.pokeCounter.domain.services.VersionGroupService
import hu.danielwolf.pokeCounter.external.api.pokemon.PokemonApi
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalPokemonForm
import hu.danielwolf.pokeCounter.external.config.toEntityMap
import hu.danielwolf.pokeCounter.external.config.toURI
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class PokemonFormSyncService(
    private val pokemonApi: PokemonApi,
    private val pokemonFormService: PokemonFormService,
    private val pokemonService: PokemonService,
    private val versionGroupService: VersionGroupService,
    private val logger: Logger
) {

    fun syncAll() {
        logger.info("Starting pokemon form sync...")
        val summaries = pokemonApi.getAllPokemonForms(0, 2000)
        summaries.results.forEach {
            val external = pokemonApi.followPokemonForm(it.url.toURI())
            val pokemon = pokemonService.getByName(external.pokemon.name)
            val versionGroup = versionGroupService.getByName(external.versionGroup.name)
            pokemonFormService.save(external.toEntity(pokemon, versionGroup))
        }
        logger.info("Finished pokemon form sync.")
    }
}

fun ExternalPokemonForm.toEntity(
    pokemon: Pokemon,
    versionGroup: VersionGroup
): PokemonForm = PokemonForm(
    id = this.id,
    name = this.name,
    order = this.order,
    formOrder = this.formOrder,
    isDefault = this.isDefault,
    isBattleOnly = this.isBattleOnly,
    isMega = this.isMega,
    formName = this.formName,
    pokemon = pokemon,
    sprite = this.sprites.frontDefault,
    versionGroup = versionGroup,
    names = this.names.toEntityMap(),
    formNames = this.formNames.toEntityMap()
)
