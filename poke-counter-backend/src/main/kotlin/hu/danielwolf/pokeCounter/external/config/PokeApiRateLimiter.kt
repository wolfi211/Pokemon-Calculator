package hu.danielwolf.pokeCounter.external.config

import java.util.concurrent.atomic.AtomicLong

/**
 * Enforces a minimum delay between consecutive PokeAPI requests to avoid rate limits.
 * Thread-safe: [acquire] is synchronized so only one request proceeds at a time.
 *
 * @param minDelayMs Minimum gap between requests in milliseconds. If <= 0, [acquire] is a no-op.
 */
class PokeApiRateLimiter(private val minDelayMs: Long) {

    private val lastRequestTimeNs = AtomicLong(0L)

    /**
     * Blocks until at least [minDelayMs] has passed since the last call to [acquire],
     * then updates the last-request timestamp. If [minDelayMs] <= 0, returns immediately.
     */
    @Synchronized
    fun acquire() {
        if (minDelayMs <= 0) return
        val now = System.nanoTime()
        val previous = lastRequestTimeNs.get()
        val elapsedMs = (now - previous) / 1_000_000
        if (elapsedMs < minDelayMs) {
            Thread.sleep(minDelayMs - elapsedMs)
        }
        lastRequestTimeNs.set(System.nanoTime())
    }
}
