package hu.danielwolf.pokeCounter.external.service

import hu.danielwolf.pokeCounter.domain.model.PokedexPokemon
import hu.danielwolf.pokeCounter.domain.model.PokedexPokemonId
import hu.danielwolf.pokeCounter.domain.model.Pokemon
import hu.danielwolf.pokeCounter.domain.model.Species
import hu.danielwolf.pokeCounter.domain.services.PokedexPokemonService
import hu.danielwolf.pokeCounter.domain.services.PokedexVersionGroupService
import hu.danielwolf.pokeCounter.domain.services.SpeciesService
import hu.danielwolf.pokeCounter.domain.repositories.PokemonRepository
import hu.danielwolf.pokeCounter.domain.repositories.PokemonFormRepository
import hu.danielwolf.pokeCounter.external.api.games.dto.ExternalPokedex
import org.slf4j.Logger
import org.springframework.stereotype.Service

/**
 * Fills pokedex_pokemon from stored ExternalPokedex list (no API calls).
 * Pass A: species-based entries; Pass B: Mega and Gigantamax forms with same entry_number as base.
 */
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

        // (pokedexId, versionGroupId, speciesId) -> entryNumber (for Pass B)
        val speciesEntryByPokedexAndVersion = mutableMapOf<Triple<Int, Int, Int>, Int>()

        // Pass A — species-based entries
        pokedexVersionGroupService.findAll().forEach { pvg ->
            val external = externalByName[pvg.pokedex.name] ?: return@forEach
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

        // Pass B — Mega and Gigantamax only (same entry_number as base)
        val megaAndGmaxForms = pokemonFormRepository.findAll().filter { form ->
            form.isMega == true || form.formName == "gmax"
        }
        megaAndGmaxForms.forEach { form ->
            val pokemon = form.pokemon ?: return@forEach
            val species = pokemon.species ?: return@forEach
            val versionGroup = form.versionGroup ?: return@forEach
            pokedexVersionGroupService.findAll()
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

    /**
     * Returns Pokemon of this species that appear in this version_group (form's version_group matches),
     * or the default variety if none.
     */
    private fun pokemonForSpeciesInVersionGroup(
        species: Species,
        versionGroupId: Int,
        formsInVersion: Map<Int, Pokemon>
    ): List<Pokemon> {
        val bySpecies = pokemonRepository.findBySpecies_Id(species.id)
        val inVersion = bySpecies.filter { formsInVersion.containsKey(it.id) }
        return if (inVersion.isNotEmpty()) {
            inVersion
        } else {
            listOfNotNull(
                bySpecies.find { it.isDefault == true } ?: bySpecies.firstOrNull()
            )
        }
    }
}
