package hu.danielwolf.pokeCounter.config

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * Returns the actual exception message and type in the response body so sync (and other) errors
 * are visible when debugging instead of a generic 400/500 with no details.
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(Throwable::class)
    fun handleAny(ex: Throwable): ResponseEntity<ErrorResponse> {
        logger.error("Unhandled exception", ex)
        val status = when (ex) {
            is IllegalArgumentException -> HttpStatus.BAD_REQUEST
            else -> HttpStatus.INTERNAL_SERVER_ERROR
        }
        return ResponseEntity
            .status(status)
            .body(
                ErrorResponse(
                    error = ex.message ?: ex.toString(),
                    exception = ex.javaClass.simpleName,
                    cause = ex.cause?.let { "${it.javaClass.simpleName}: ${it.message}" }
                )
            )
    }
}

data class ErrorResponse(
    val error: String,
    val exception: String,
    val cause: String? = null
)
