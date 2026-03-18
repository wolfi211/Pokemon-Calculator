@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.service.pokemon

import hu.danielwolf.pokeCounter.domain.model.games.Generation
import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokemonAbility
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokemonMove
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokemonStat
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokemonType
import hu.danielwolf.pokeCounter.domain.model.pokemon.Species
import hu.danielwolf.pokeCounter.domain.service.games.GenerationService
import hu.danielwolf.pokeCounter.domain.service.games.VersionGroupService
import hu.danielwolf.pokeCounter.domain.service.moves.MoveLearnMethodService
import hu.danielwolf.pokeCounter.domain.service.moves.MoveService
import hu.danielwolf.pokeCounter.domain.service.pokemon.AbilityService
import hu.danielwolf.pokeCounter.domain.service.pokemon.PokemonAbilityService
import hu.danielwolf.pokeCounter.domain.service.pokemon.PokemonMoveService
import hu.danielwolf.pokeCounter.domain.service.pokemon.PokemonService
import hu.danielwolf.pokeCounter.domain.service.pokemon.PokemonStatService
import hu.danielwolf.pokeCounter.domain.service.pokemon.PokemonTypeService
import hu.danielwolf.pokeCounter.domain.service.pokemon.SpeciesService
import hu.danielwolf.pokeCounter.domain.service.pokemon.StatService
import hu.danielwolf.pokeCounter.domain.service.pokemon.TypeService
import hu.danielwolf.pokeCounter.pokeApi.api.pokemon.PokemonApi
import hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto.ExternalPokemon
import hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto.ExternalPokemonAbility
import hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto.ExternalPokemonStat
import hu.danielwolf.pokeCounter.pokeApi.api.pokemon.dto.ExternalPokemonType
import hu.danielwolf.pokeCounter.pokeApi.config.toURI
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

  fun syncAll() {
    logger.info("Starting pokemon sync...")
    val externalPokemons = pokemonApi.getAllPokemon(limit = 10000)
    externalPokemons.results.forEach { entry ->
      val externalPokemon = pokemonApi.followPokemon(entry.url.toURI())
      val species = speciesService.getByName(externalPokemon.species.name)
      val pokemon = pokemonService.save(externalPokemon.toEntity(species))
      fillPokemonTypes(pokemon, externalPokemon)
      fillPokemonAbilities(pokemon, externalPokemon)
      fillPokemonStats(pokemon, externalPokemon)
      fillPokemonMoves(pokemon, externalPokemon)
    }
    logger.info("Finished pokemon sync (${externalPokemons.count} pokemon).")
  }

  private fun fillPokemonTypes(pokemon: Pokemon, external: ExternalPokemon) {
    val types = mutableListOf<PokemonType>()
    external.types.forEach { t ->
      addPokemonType(t, types, pokemon)
    }
    external.pastTypes.forEach { past ->
      val generation = generationService.getByName(past.generation.name)
      past.types.forEach { t ->
        addPokemonType(t, types, pokemon, generation)
      }
    }
    if (types.isNotEmpty()) pokemonTypeService.saveAll(types)
  }

  private fun addPokemonType(
    t: ExternalPokemonType,
    types: MutableList<PokemonType>,
    pokemon: Pokemon,
    generation: Generation? = null
  ) {
    val type = typeService.getByName(t.type.name)
    types.add(
      pokemonTypeService.getBySlotAndPokemonAndGeneration(t.slot, pokemon, null)?.apply {
        this.type = type
      }
        ?: PokemonType(
          pokemon = pokemon,
          slot = t.slot,
          type = type,
          generation = generation
        )
    )
  }

  private fun fillPokemonAbilities(pokemon: Pokemon, external: ExternalPokemon) {
    val abilities = mutableListOf<PokemonAbility>()
    external.abilities.forEach { a ->
      addPokemonAbility(abilities, a, pokemon)
    }
    external.pastAbilities.forEach { past ->
      val generation = generationService.getByName(past.generation.name)
      past.abilities.forEach { a ->
        addPokemonAbility(abilities, a, pokemon, generation)
      }
    }
    if (abilities.isNotEmpty()) pokemonAbilityService.saveAll(abilities)
  }

  private fun addPokemonAbility(
    abilities: MutableList<PokemonAbility>,
    ability: ExternalPokemonAbility,
    pokemon: Pokemon,
    generation: Generation? = null
  ) {
    val a = ability.ability?.let { abilityService.getByName(it.name) }
    abilities.add(
      pokemonAbilityService.getByPokemonAndSlotAndGeneration(pokemon, ability.slot, generation)?.apply {
        this.ability = a
        this.isHidden = ability.isHidden
      } ?: PokemonAbility(
        pokemon = pokemon,
        ability = ability.ability?.let { abilityService.getByName(it.name) },
        isHidden = ability.isHidden,
        slot = ability.slot,
        generation = generation
      )
    )
  }

  private fun fillPokemonStats(pokemon: Pokemon, external: ExternalPokemon) {
    val stats = mutableListOf<PokemonStat>()
    external.stats.forEach { s ->
      addPokemonStat(stats, s, pokemon)
    }
    external.pastStats.forEach { past ->
      val generation = generationService.getByName(past.generation.name)
      past.stats.forEach { s ->
        addPokemonStat(stats, s, pokemon, generation)
      }
    }
    if (stats.isNotEmpty()) pokemonStatService.saveAll(stats)
  }

  private fun addPokemonStat(
    stats: MutableList<PokemonStat>,
    s: ExternalPokemonStat,
    pokemon: Pokemon,
    generation: Generation? = null
  ) {
    val stat = statService.getByName(s.stat.name)

    stats.add(
      pokemonStatService.getByStatAndPokemonAndGeneration(stat, pokemon, generation)?.apply {
        this.baseStat = s.baseStat
        this.effort = s.effort
      } ?: PokemonStat(
        pokemon = pokemon,
        stat = statService.getByName(s.stat.name),
        generation = generation,
        baseStat = s.baseStat,
        effort = s.effort
      )
    )
  }

  private fun fillPokemonMoves(pokemon: Pokemon, external: ExternalPokemon) {
    val movesByKey = mutableMapOf<String, PokemonMove>()
    external.moves.forEach { m ->
      val move = moveService.getByName(m.move.name)
      m.versionGroupDetails.forEach { vgd ->
        val learnMethod = moveLearnMethodService.getByName(vgd.moveLearnMethod.name)
        val versionGroup = versionGroupService.getByName(vgd.versionGroup.name)
        val key = "${pokemon.id}_${move.id}_${versionGroup.id}"
        val existing =
          movesByKey[key] ?: pokemonMoveService.getByPokemonAndMoveAndVersionGroup(pokemon, move, versionGroup)

        if (existing != null) {
          existing.moveLearnMethod = learnMethod
          existing.levelLearnedAt = vgd.levelLearnedAt
          existing.order = vgd.order
          if (movesByKey[key] == null) movesByKey[key] = existing
        } else {
          movesByKey[key] =
            PokemonMove(
              pokemon = pokemon,
              move = move,
              moveLearnMethod = learnMethod,
              versionGroup = versionGroup,
              levelLearnedAt = vgd.levelLearnedAt,
              order = vgd.order
            )
        }
      }
    }
    val moves = movesByKey.values.toList()
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
  sprite = (this.sprites.other?.get("official-artwork") as? Map<*, *>)?.get("front_default") as? String ?: this.sprites.frontDefault,
  cry = this.cries.latest,
  species = species
)
