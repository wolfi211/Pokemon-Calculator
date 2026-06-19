package hu.danielwolf.pokeCounter.api.pokemon

import hu.danielwolf.pokeCounter.api.pokemon.dto.MinifiedPokemonSearchResponse
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
@RequestMapping("/api/v1/pokemon")
@Tag(name = "Pokemon", description = "Pokémon search and listing for the UI")
class PokemonApi(
    private val pokemonApiService: PokemonApiService,
) {

    @Operation(
        summary = "Search Pokémon (dropdown / autocomplete)",
        description =
            "Returns a minified list for searchable dropdowns. " +
                "Text match: case-insensitive substring against the PokéAPI slug (name) and every value " +
                "in the related species names JSON (all language codes). " +
                "Empty or omitted query: no text filter. " +
                "versionGroup: when set, only Pokémon in pokedex_pokemon for that PokéAPI version-group id; " +
                "when omitted, no Pokédex filter.",
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Matching Pokémon, ordered by id",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        array = ArraySchema(schema = Schema(implementation = MinifiedPokemonSearchResponse::class)),
                    ),
                ],
            ),
            ApiResponse(responseCode = "500", description = "Internal server error", content = [Content()]),
        ],
    )
    @GetMapping("/minified-search")
    fun minifiedList(
        @Parameter(
            description = "Substring to match against slug and all localized species names. Omit or leave empty for no text filter.",
            required = false,
            example = "bulba",
        )
        @RequestParam(required = false)
        query: String?,
        @Parameter(
            description = "PokéAPI version group id. If set, restricts to Pokémon in a Pokédex for that group.",
            required = false,
            example = "17",
        )
        @RequestParam(required = false)
        versionGroup: Int?,
    ): List<MinifiedPokemonSearchResponse> =
        pokemonApiService.searchPokemonMinified(query, versionGroup)
}
