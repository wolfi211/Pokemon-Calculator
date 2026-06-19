package hu.danielwolf.pokeCounter.domain.service.pokemon

import hu.danielwolf.pokeCounter.domain.model.pokemon.Type
import hu.danielwolf.pokeCounter.domain.model.pokemon.TypeRelation
import hu.danielwolf.pokeCounter.domain.repository.pokemon.TypeRelationRepository
import java.math.BigDecimal
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class TypeEffectivenessServiceTest {

  @Mock
  private lateinit var typeRelationRepository: TypeRelationRepository

  @InjectMocks
  private lateinit var typeEffectivenessService: TypeEffectivenessService

  @Test
  fun getMultiplier_usesCurrentGenerationChart() {
    whenever(typeRelationRepository.findByGenerationIsNull()).thenReturn(
      listOf(
        relation(fromId = 10, toId = 12, multiplier = "2"),
        relation(fromId = 1, toId = 8, multiplier = "0"),
      ),
    )

    assertEquals(2.0, typeEffectivenessService.getMultiplier(10, listOf(12)))
    assertEquals(0.0, typeEffectivenessService.getMultiplier(1, listOf(8)))
    assertEquals(1.0, typeEffectivenessService.getMultiplier(10, listOf(11)))
    assertEquals(4.0, typeEffectivenessService.getMultiplier(10, listOf(12, 12)))
  }

  private fun relation(
    fromId: Int,
    toId: Int,
    multiplier: String,
  ): TypeRelation {
    val from = Type(id = fromId, name = "from-$fromId")
    val to = Type(id = toId, name = "to-$toId")
    return TypeRelation(
      damageFromType = from,
      damageToType = to,
      multiplier = BigDecimal(multiplier),
      generation = null,
    )
  }
}
