@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.service

import hu.danielwolf.pokeCounter.domain.model.pokemon.PokedexPokemon
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokedexPokemonId
import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon
import hu.danielwolf.pokeCounter.domain.model.pokemon.Species
import hu.danielwolf.pokeCounter.domain.repository.pokemon.PokemonFormRepository
import hu.danielwolf.pokeCounter.domain.repository.pokemon.PokemonRepository
import hu.danielwolf.pokeCounter.domain.service.games.PokedexVersionGroupService
import hu.danielwolf.pokeCounter.domain.service.pokemon.PokedexPokemonService
import hu.danielwolf.pokeCounter.domain.service.pokemon.SpeciesService
import hu.danielwolf.pokeCounter.pokeApi.api.games.dto.ExternalPokedex
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class PokedexPokemonFillService(
    private val pokedexPokemonService: PokedexPokemonService,
    private val pokedexVersionGroupService: PokedexVersionGroupService,
    private val speciesService: SpeciesService,
    private val pokemonRepository: PokemonRepository,
    private val pokemonFormRepository: PokemonFormRepository,
    private val logger: Logger
) {

    fun fill(externalPokedexes: List<ExternalPokedex>) {
        if (externalPokedexes.isEmpty()) {
            logger.warn("No external pokedexes stored; skipping PokedexPokemon fill.")
            return
        }
        logger.info("PokedexPokemon fill starting (${externalPokedexes.size} pokedexes)...")
        pokedexPokemonService.deleteAll()
        val externalByName = externalPokedexes.associateBy { it.name }

        val speciesEntryByPokedexAndVersion = mutableMapOf<Triple<Int, Int, Int>, Int>()

        val allPvg = pokedexVersionGroupService.findAllWithPokedexAndVersionGroup()
        logger.info("Pass A: processing ${allPvg.size} pokedex-version-groups...")
        allPvg.forEachIndexed { index, pvg ->
            if ((index + 1) % 10 == 0 || index == 0) {
                logger.info("Pass A: ${index + 1}/${allPvg.size} pokedex-version-groups")
            }
            val external = externalByName[pvg.pokedex.name] ?: return@forEachIndexed
            val pokedex = pvg.pokedex
            val versionGroup = pvg.versionGroup
            val formsInVersion = pokemonFormRepository.findByVersionGroup_Id(versionGroup.id)
                .mapNotNull { it.pokemon }
                .associateBy { it.id }

            external.pokemonEntries.forEach { entry ->
                val species = speciesService.getByName(entry.pokemonSpecies.name)
                val pokemonToInsert = pokemonForSpeciesInVersionGroup(
                    species,
                    versionGroup.id,
                    formsInVersion
                )
                speciesEntryByPokedexAndVersion[Triple(pokedex.id, versionGroup.id, species.id)] = entry.entryNumber
                pokemonToInsert.forEach { pokemon ->
                    val id = PokedexPokemonId(pokedex.id, versionGroup.id, pokemon.id)
                    pokedexPokemonService.save(
                        PokedexPokemon(
                            id = id,
                            pokedex = pokedex,
                            versionGroup = versionGroup,
                            pokemon = pokemon,
                            entryNumber = entry.entryNumber
                        )
                    )
                }
            }
        }

        val megaAndGmaxForms = pokemonFormRepository.findAllMegaAndGmaxWithPokemonAndVersionGroup()
        logger.info("Pass B: processing ${megaAndGmaxForms.size} mega/gigantamax forms...")
        megaAndGmaxForms.forEachIndexed { index, form ->
            if ((index + 1) % 50 == 0 || index == 0) {
                logger.info("Pass B: ${index + 1}/${megaAndGmaxForms.size} forms")
            }
            val pokemon = form.pokemon ?: return@forEachIndexed
            val species = pokemon.species ?: return@forEachIndexed
            val versionGroup = form.versionGroup ?: return@forEachIndexed
            pokedexVersionGroupService.findAllWithPokedexAndVersionGroup()
                .filter { it.versionGroup.id == versionGroup.id }
                .forEach { pvg ->
                    val entryNumber = speciesEntryByPokedexAndVersion[Triple(pvg.pokedex.id, versionGroup.id, species.id)]
                        ?: return@forEach
                    val id = PokedexPokemonId(pvg.pokedex.id, versionGroup.id, pokemon.id)
                    if (!pokedexPokemonService.existsById(id)) {
                        pokedexPokemonService.save(
                            PokedexPokemon(
                                id = id,
                                pokedex = pvg.pokedex,
                                versionGroup = versionGroup,
                                pokemon = pokemon,
                                entryNumber = entryNumber
                            )
                        )
                    }
                }
        }

        logger.info("PokedexPokemon fill finished.")
    }

    private fun pokemonForSpeciesInVersionGroup(
        species: Species,
        versionGroupId: Int,
        formsInVersion: Map<Int, Pokemon>
    ): List<Pokemon> {
        val bySpecies = pokemonRepository.findBySpecies_Id(species.id)
        val inVersion = bySpecies.filter { formsInVersion.containsKey(it.id) }
        return inVersion.ifEmpty {
          listOfNotNull(
            bySpecies.find { it.isDefault == true } ?: bySpecies.firstOrNull()
          )
        }
    }
}
