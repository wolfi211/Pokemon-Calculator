package hu.danielwolf.pokeCounter.api.counter

import hu.danielwolf.pokeCounter.api.counter.dto.CounterFindRequest
import hu.danielwolf.pokeCounter.api.counter.dto.CounterFindResponse
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@WebMvcTest(CounterApi::class)
class CounterApiTest {

  @Autowired
  private lateinit var mockMvc: MockMvc

  @MockitoBean
  private lateinit var counterApiService: CounterApiService

  @Test
  fun findCounters_returnsOk() {
    whenever(counterApiService.findCounters(CounterFindRequest(enemyPokemonId = 1, enemyMoveIds = listOf(91))))
      .thenReturn(CounterFindResponse(results = emptyList()))

    mockMvc
      .post("/api/v1/counters/find") {
        contentType = MediaType.APPLICATION_JSON
        content = """{"enemyPokemonId":1,"enemyMoveIds":[91]}"""
      }.andExpect {
        status { isOk() }
      }
  }
}
