package hu.danielwolf.pokeCounter.external.service.pokemon

import hu.danielwolf.pokeCounter.domain.entities.Pokemon
import hu.danielwolf.pokeCounter.domain.entities.PokemonAbility
import hu.danielwolf.pokeCounter.domain.entities.PokemonMove
import hu.danielwolf.pokeCounter.domain.entities.PokemonStat
import hu.danielwolf.pokeCounter.domain.entities.PokemonType
import hu.danielwolf.pokeCounter.domain.entities.PokemonTypeId
import hu.danielwolf.pokeCounter.domain.entities.Species
import hu.danielwolf.pokeCounter.domain.services.AbilityService
import hu.danielwolf.pokeCounter.domain.services.GenerationService
import hu.danielwolf.pokeCounter.domain.services.MoveLearnMethodService
import hu.danielwolf.pokeCounter.domain.services.MoveService
import hu.danielwolf.pokeCounter.domain.services.PokemonAbilityService
import hu.danielwolf.pokeCounter.domain.services.PokemonMoveService
import hu.danielwolf.pokeCounter.domain.services.PokemonStatService
import hu.danielwolf.pokeCounter.domain.services.PokemonService
import hu.danielwolf.pokeCounter.domain.services.PokemonTypeService
import hu.danielwolf.pokeCounter.domain.services.SpeciesService
import hu.danielwolf.pokeCounter.domain.services.StatService
import hu.danielwolf.pokeCounter.domain.services.TypeService
import hu.danielwolf.pokeCounter.domain.services.VersionGroupService
import hu.danielwolf.pokeCounter.external.api.pokemon.PokemonApi
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalPokemon
import hu.danielwolf.pokeCounter.external.api.pokemon.dto.ExternalPokemonSpecies
import hu.danielwolf.pokeCounter.external.config.toURI
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class PokemonSyncService(
    private val pokemonApi: PokemonApi,
    private val pokemonService: PokemonService,
    private val speciesService: SpeciesService,
    private val pokemonTypeService: PokemonTypeService,
    private val pokemonAbilityService: PokemonAbilityService,
    private val pokemonStatService: PokemonStatService,
    private val pokemonMoveService: PokemonMoveService,
    private val typeService: TypeService,
    private val abilityService: AbilityService,
    private val statService: StatService,
    private val generationService: GenerationService,
    private val moveService: MoveService,
    private val moveLearnMethodService: MoveLearnMethodService,
    private val versionGroupService: VersionGroupService,
    private val logger: Logger
) {

    /**
     * Syncs all Pokemon from species varieties and fills PokemonType, PokemonAbility, PokemonStat, PokemonMove.
     */
    fun syncAll(externalSpeciesList: List<ExternalPokemonSpecies>) {
        logger.info("Starting pokemon sync (from ${externalSpeciesList.size} species)...")
        var count = 0
        externalSpeciesList.forEach { externalSpecies ->
            externalSpecies.varieties.forEach { variety ->
                try {
                    val external = pokemonApi.followPokemon(variety.pokemon.url.toURI())
                    val species = speciesService.getByName(externalSpecies.name)
                    val pokemon = pokemonService.save(external.toEntity(species))
                    fillPokemonTypes(pokemon, external)
                    fillPokemonAbilities(pokemon, external)
                    fillPokemonStats(pokemon, external)
                    fillPokemonMoves(pokemon, external)
                    count++
                } catch (e: Exception) {
                    logger.error("Error syncing pokemon ${variety.pokemon.name}: ${e.message}")
                }
            }
        }
        logger.info("Finished pokemon sync ($count pokemon).")
    }

    private fun fillPokemonTypes(pokemon: Pokemon, external: ExternalPokemon) {
        val types = mutableListOf<PokemonType>()
        external.types.forEach { t ->
            types.add(
                PokemonType(
                    id = PokemonTypeId(pokemon.id, t.slot, null),
                    pokemon = pokemon,
                    generation = null,
                    type = typeService.getByName(t.type.name)
                )
            )
        }
        external.pastTypes.forEach { past ->
            val generation = generationService.getByName(past.generation.name)
            past.types.forEach { t ->
                types.add(
                    PokemonType(
                        id = PokemonTypeId(pokemon.id, t.slot, generation.id),
                        pokemon = pokemon,
                        generation = generation,
                        type = typeService.getByName(t.type.name)
                    )
                )
            }
        }
        if (types.isNotEmpty()) pokemonTypeService.saveAll(types)
    }

    private fun fillPokemonAbilities(pokemon: Pokemon, external: ExternalPokemon) {
        val abilities = mutableListOf<PokemonAbility>()
        external.abilities.forEach { a ->
            abilities.add(
                PokemonAbility(
                    pokemon = pokemon,
                    ability = abilityService.getByName(a.ability.name),
                    isHidden = a.isHidden,
                    slot = a.slot,
                    generation = null
                )
            )
        }
        external.pastAbilities.forEach { past ->
            val generation = generationService.getByName(past.generation.name)
            past.abilities.forEach { a ->
                abilities.add(
                    PokemonAbility(
                        pokemon = pokemon,
                        ability = abilityService.getByName(a.ability.name),
                        isHidden = a.isHidden,
                        slot = a.slot,
                        generation = generation
                    )
                )
            }
        }
        if (abilities.isNotEmpty()) pokemonAbilityService.saveAll(abilities)
    }

    private fun fillPokemonStats(pokemon: Pokemon, external: ExternalPokemon) {
        val stats = mutableListOf<PokemonStat>()
        external.stats.forEach { s ->
            stats.add(
                PokemonStat(
                    pokemon = pokemon,
                    stat = statService.getByName(s.stat.name),
                    generation = null,
                    baseStat = s.baseStat,
                    effort = s.effort
                )
            )
        }
        external.pastStats.forEach { past ->
            val generation = generationService.getByName(past.generation.name)
            past.stats.forEach { s ->
                stats.add(
                    PokemonStat(
                        pokemon = pokemon,
                        stat = statService.getByName(s.stat.name),
                        generation = generation,
                        baseStat = s.baseStat,
                        effort = s.effort
                    )
                )
            }
        }
        if (stats.isNotEmpty()) pokemonStatService.saveAll(stats)
    }

    private fun fillPokemonMoves(pokemon: Pokemon, external: ExternalPokemon) {
        val moves = mutableListOf<PokemonMove>()
        external.moves.forEach { m ->
            val move = moveService.getByName(m.move.name)
            m.versionGroupDetails.forEach { vgd ->
                moves.add(
                    PokemonMove(
                        pokemon = pokemon,
                        move = move,
                        moveLearnMethod = moveLearnMethodService.getByName(vgd.moveLearnMethod.name),
                        versionGroup = versionGroupService.getByName(vgd.versionGroup.name),
                        levelLearnedAt = vgd.levelLearnedAt,
                        order = vgd.order
                    )
                )
            }
        }
        if (moves.isNotEmpty()) pokemonMoveService.saveAll(moves)
    }
}

fun ExternalPokemon.toEntity(species: Species): Pokemon = Pokemon(
    id = this.id,
    name = this.name,
    baseExperience = this.baseExperience,
    height = this.height,
    isDefault = this.isDefault,
    order = this.order,
    weight = this.weight,
    sprite = this.sprites.frontDefault,
    cry = this.cries.latest,
    species = species
)
