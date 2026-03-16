package hu.danielwolf.pokeCounter.api.sync

import hu.danielwolf.pokeCounter.external.service.PokeApiSyncService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/sync")
class SyncController(
    private val pokeApiSyncService: PokeApiSyncService,
) {

    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Successful PokeAPI sync",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = ResponseEntity::class)
                    )
                ]
            ),
            ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
            ApiResponse(responseCode = "500", description = "Internal server error", content = [Content()])
        ]
    )
    @PostMapping
    @Operation(summary = "Start Poke API Sync")
    fun syncAll(): ResponseEntity<SyncResponse> {
        pokeApiSyncService.syncAll()
        return ResponseEntity.ok(SyncResponse(message = "PokeAPI full sync completed."))
    }
}

data class SyncResponse(val message: String)
