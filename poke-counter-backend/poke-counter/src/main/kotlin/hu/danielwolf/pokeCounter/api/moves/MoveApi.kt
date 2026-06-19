package hu.danielwolf.pokeCounter.api.moves

import hu.danielwolf.pokeCounter.api.moves.dto.MinifiedMoveSearchResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/moves")
@Tag(name = "Moves", description = "Move search and listing for the UI")
class MoveApi(
  private val moveApiService: MoveApiService,
) {
  @Operation(
    summary = "Search moves (dropdown / autocomplete)",
    description =
      "Returns a minified list for searchable dropdowns. " +
        "Text match: case-insensitive substring against the PokéAPI slug (name) and English (`en`) " +
        "display name in the move names JSON. " +
        "Empty or omitted query: no text filter. " +
        "pokemonId: when set, only moves that Pokémon can learn (pokemon_moves); the move's type is " +
        "chosen from move_types using a version group that appears in that learnset (highest such id). " +
        "When omitted, no learnset filter and the type row with the highest version group id is used.",
  )
  @ApiResponses(
    value = [
      ApiResponse(
        responseCode = "200",
        description = "Matching moves, ordered by id",
        content = [
          Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            array = ArraySchema(schema = Schema(implementation = MinifiedMoveSearchResponse::class)),
          ),
        ],
      ),
      ApiResponse(responseCode = "500", description = "Internal server error", content = [Content()]),
    ],
  )
  @GetMapping("/minified-search")
  fun minifiedList(
    @Parameter(
      description = "Substring to match against slug and English move name. Omit or leave empty for no text filter.",
      required = false,
      example = "flame",
    )
    @RequestParam(required = false)
    query: String?,
    @Parameter(
      description =
        "PokéAPI Pokémon id. If set, only moves this Pokémon can learn; typing is aligned to learnable version groups.",
      required = false,
      example = "6",
    )
    @RequestParam(required = false)
    pokemonId: Int?,
  ): List<MinifiedMoveSearchResponse> =
    moveApiService.searchMovesMinified(query, pokemonId)
}
