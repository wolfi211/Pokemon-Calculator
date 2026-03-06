package hu.danielwolf.pokeCounter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class PokeCounterBackendApplication

fun main(args: Array<String>) {
  runApplication<PokeCounterBackendApplication>(*args)
}
