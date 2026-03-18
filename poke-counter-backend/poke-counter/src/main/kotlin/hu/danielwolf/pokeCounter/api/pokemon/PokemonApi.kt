package hu.danielwolf.pokeCounter.api.pokemon

import hu.danielwolf.pokeCounter.api.pokemon.dto.MinifiedPokemonSearchResponse
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonApi {

  fun minifiedList(): List<MinifiedPokemonSearchResponse> {}

}