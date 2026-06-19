package hu.danielwolf.pokeCounter.api.counter

import hu.danielwolf.pokeCounter.api.counter.dto.CounterFindRequest
import hu.danielwolf.pokeCounter.api.counter.dto.CounterFindResponse
import hu.danielwolf.pokeCounter.api.counter.dto.CounterResultDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/counters")
@Tag(name = "Counters", description = "Pokémon counter finder")
class CounterApi(
  private val counterApiService: CounterApiService,
) {
  @Operation(
    summary = "Find counter Pokémon",
    description =
      "Given an enemy Pokémon and its moves, returns candidate counters ranked by defensive tier. " +
        "Status moves are ignored. Candidates must take no super-effective damage from the enemy moves " +
        "and must have a learnable physical or special move that is super-effective against the enemy.",
  )
  @ApiResponses(
    value = [
      ApiResponse(
        responseCode = "200",
        description = "Matching counters ordered by tier (lower is better)",
        content = [
          Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = Schema(implementation = CounterFindResponse::class),
          ),
        ],
      ),
      ApiResponse(responseCode = "400", description = "Invalid request or missing Pokémon", content = [Content()]),
      ApiResponse(responseCode = "500", description = "Internal server error", content = [Content()]),
    ],
  )
  @PostMapping("/find")
  fun findCounters(
    @RequestBody request: CounterFindRequest,
  ): CounterFindResponse = counterApiService.findCounters(request)
}
