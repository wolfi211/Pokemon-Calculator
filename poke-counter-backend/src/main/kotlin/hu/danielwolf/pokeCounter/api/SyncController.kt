package hu.danielwolf.pokeCounter.api

import hu.danielwolf.pokeCounter.external.service.PokeApiSyncService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class SyncController(
    private val pokeApiSyncService: PokeApiSyncService,
) {

    /**
     * Manually trigger a full PokeAPI sync (Phase 1 → 2 → 3 → 4 → PokedexPokemon fill).
     * This is long-running; the response is returned when the sync completes.
     */
    @PostMapping("/sync")
    fun syncAll(): ResponseEntity<SyncResponse> {
        pokeApiSyncService.syncAll()
        return ResponseEntity.ok(SyncResponse(message = "PokeAPI full sync completed."))
    }
}

data class SyncResponse(val message: String)
