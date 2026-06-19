package hu.danielwolf.pokeCounter.domain.service.counter

import hu.danielwolf.pokeCounter.domain.model.moves.DamageClass
import hu.danielwolf.pokeCounter.domain.model.moves.Move
import hu.danielwolf.pokeCounter.domain.model.moves.MoveType
import hu.danielwolf.pokeCounter.domain.model.pokemon.Pokemon
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokemonMove
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokemonStat
import hu.danielwolf.pokeCounter.domain.model.pokemon.PokemonType
import hu.danielwolf.pokeCounter.domain.model.pokemon.Stat
import hu.danielwolf.pokeCounter.domain.model.pokemon.Type
import hu.danielwolf.pokeCounter.domain.model.games.VersionGroup
import hu.danielwolf.pokeCounter.domain.model.moves.MoveLearnMethod
import hu.danielwolf.pokeCounter.domain.repository.moves.MoveRepository
import hu.danielwolf.pokeCounter.domain.repository.pokemon.PokemonMoveRepository
import hu.danielwolf.pokeCounter.domain.service.pokemon.PokemonService
import hu.danielwolf.pokeCounter.domain.service.pokemon.TypeEffectivenessService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class CounterServiceTest {

  @Mock
  private lateinit var pokemonService: PokemonService

  @Mock
  private lateinit var moveRepository: MoveRepository

  @Mock
  private lateinit var pokemonMoveRepository: PokemonMoveRepository

  @Mock
  private lateinit var typeEffectivenessService: TypeEffectivenessService

  @InjectMocks
  private lateinit var counterService: CounterService

  @Test
  fun findBestCounters_excludesWeakCandidatesAndRanksByTier() {
    val fire = type(10, "fire")
    val water = type(11, "water")
    val grass = type(12, "grass")

    val enemy = pokemon(id = 1, types = listOf(pokemonType(stubPokemon(1), 1, fire)))
    attachStats(enemy, attack = 50, specialAttack = 50)

    val strongCounter =
      pokemon(id = 2, types = listOf(pokemonType(stubPokemon(2), 1, water)))
    attachStats(strongCounter, attack = 80, specialAttack = 40)

    val weakCandidate =
      pokemon(id = 3, types = listOf(pokemonType(stubPokemon(3), 1, grass)))
    attachStats(weakCandidate, attack = 80, specialAttack = 40)

    whenever(pokemonService.getByIdWithSummaryGraph(1)).thenReturn(enemy)
    whenever(moveRepository.findAllByIdWithDamageClassAndTypes(listOf(91))).thenReturn(
      listOf(
        move(
          id = 91,
          damageClassName = "special",
          moveType = fire,
        ),
      ),
    )
    whenever(typeEffectivenessService.getMultiplier(10, listOf(11))).thenReturn(0.5)
    whenever(typeEffectivenessService.getMultiplier(10, listOf(12))).thenReturn(2.0)
    whenever(typeEffectivenessService.getMultiplier(11, listOf(10))).thenReturn(2.0)
    whenever(pokemonService.findAllWithTypesAndCurrentStats()).thenReturn(listOf(strongCounter, weakCandidate))
    whenever(pokemonMoveRepository.findAllByPokemonIdInWithMoveGraph(listOf(2))).thenReturn(
      listOf(
        pokemonMove(
          pokemon = strongCounter,
          move = move(id = 55, damageClassName = "physical", moveType = water),
        ),
      ),
    )

    val results =
      counterService.findBestCounters(
        CounterSearchInput(enemyPokemonId = 1, enemyMoveIds = listOf(91)),
      )

    assertEquals(1, results.size)
    assertEquals(2, results.first().pokemon.id)
    assertEquals(0, results.first().tier)
    assertTrue(results.first().hasStab)
  }

  private fun stubPokemon(id: Int): Pokemon =
    Pokemon(
      id = id,
      name = "p$id",
      baseExperience = null,
      height = null,
      isDefault = true,
      order = id,
      weight = null,
      sprite = null,
      cry = null,
      species = null,
    )

  private fun pokemonType(
    pokemon: Pokemon,
    slot: Int,
    type: Type,
  ): PokemonType =
    PokemonType(
      pokemon = pokemon,
      slot = slot,
      type = type,
      generation = null,
    )

  private fun pokemon(
    id: Int,
    types: List<PokemonType>,
  ): Pokemon {
    val pokemon = stubPokemon(id)
    types.forEach { it.pokemon = pokemon }
    pokemon.types.addAll(types)
    return pokemon
  }

  private fun attachStats(
    pokemon: Pokemon,
    attack: Int,
    specialAttack: Int,
  ) {
    pokemon.stats.addAll(
      listOf(
        statRow(pokemon, "attack", attack),
        statRow(pokemon, "special-attack", specialAttack),
        statRow(pokemon, "hp", 50),
        statRow(pokemon, "defense", 50),
        statRow(pokemon, "special-defense", 50),
        statRow(pokemon, "speed", 50),
      ),
    )
  }

  private fun statRow(
    pokemon: Pokemon,
    name: String,
    value: Int,
  ): PokemonStat =
    PokemonStat(
      pokemon = pokemon,
      stat = Stat(id = name.hashCode(), name = name),
      generation = null,
      baseStat = value,
      effort = 0,
    )

  private fun type(
    id: Int,
    name: String,
  ): Type = Type(id = id, name = name)

  private fun move(
    id: Int,
    damageClassName: String,
    moveType: Type,
  ): Move {
    val move =
      Move(
        id = id,
        name = "move-$id",
        priority = 0,
        damageClass = DamageClass(id = damageClassName.hashCode(), name = damageClassName),
        generation = null,
      )
    move.types.add(
      MoveType(
        move = move,
        type = moveType,
        versionGroup = null,
        power = 80,
        accuracy = 100,
        pp = 10,
        effectChance = null,
      ),
    )
    return move
  }

  private fun pokemonMove(
    pokemon: Pokemon,
    move: Move,
  ): PokemonMove =
    PokemonMove(
      pokemon = pokemon,
      move = move,
      moveLearnMethod = MoveLearnMethod(id = 1, name = "level-up"),
      versionGroup = VersionGroup(id = 1, name = "red-blue", order = 1, generation = null),
      levelLearnedAt = 1,
      order = null,
    )
}
