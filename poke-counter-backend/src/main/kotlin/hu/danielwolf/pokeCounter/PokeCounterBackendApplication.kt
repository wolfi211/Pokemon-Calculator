package hu.danielwolf.pokeCounter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PokeCounterBackendApplication

fun main(args: Array<String>) {
  runApplication<PokeCounterBackendApplication>(*args)
}
