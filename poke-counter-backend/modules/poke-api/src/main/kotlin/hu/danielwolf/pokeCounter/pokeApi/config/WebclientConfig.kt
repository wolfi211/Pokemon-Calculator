@file:Suppress("UNUSED_PARAMETER", "UNUSED", "unused")

package hu.danielwolf.pokeCounter.pokeApi.config

import hu.danielwolf.pokeCounter.pokeApi.api.berries.BerryApiClient
import hu.danielwolf.pokeCounter.pokeApi.api.contests.ContestApiClient
import hu.danielwolf.pokeCounter.pokeApi.api.encounters.EncounterApiClient
import hu.danielwolf.pokeCounter.pokeApi.api.evolution.EvolutionApiClient
import hu.danielwolf.pokeCounter.pokeApi.api.games.GameApiClient
import hu.danielwolf.pokeCounter.pokeApi.api.items.ItemApiClient
import hu.danielwolf.pokeCounter.pokeApi.api.locations.LocationApiClient
import hu.danielwolf.pokeCounter.pokeApi.api.machines.MachineApiClient
import hu.danielwolf.pokeCounter.pokeApi.api.move.MoveApiClient
import hu.danielwolf.pokeCounter.pokeApi.api.pokemon.PokemonApiClient
import hu.danielwolf.pokeCounter.pokeApi.api.utilities.UtilityApiClient
import io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS
import java.time.Duration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.http.codec.json.JacksonJsonDecoder
import org.springframework.http.codec.json.JacksonJsonEncoder
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import org.springframework.web.service.invoker.createClient
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.kotlin.KotlinModule

@Configuration
class WebclientConfig(private val pokeApiProperties: PokeApiProperties) {

  @Bean
  fun pokeWebClient(): WebClient {
    val jsonMapper =
      JsonMapper.builder()
        .addModule(KotlinModule.Builder().build())
        .build()
    val decoder = JacksonJsonDecoder(jsonMapper, MediaType.APPLICATION_JSON).apply {
      setMaxInMemorySize(10 * 1024 * 1024)
    }
    val encoder = JacksonJsonEncoder(jsonMapper, MediaType.APPLICATION_JSON)
    return WebClient.builder()
      .baseUrl(pokeApiProperties.baseUrl)
      .exchangeStrategies(
        ExchangeStrategies.builder().codecs { configurer ->
          configurer.defaultCodecs().jacksonJsonDecoder(decoder)
          configurer.defaultCodecs().jacksonJsonEncoder(encoder)
          configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)
        }.build()
      )
      .filter { request, next ->
        Mono.delay(Duration.ofMillis(pokeApiProperties.minDelayBetweenRequestsMs))
          .then(next.exchange(request))
      }
      .clientConnector(
        ReactorClientHttpConnector(
          HttpClient.create()
            .responseTimeout(Duration.ofSeconds(RESPONSE_TIMEOUT_SECONDS))
            .option(CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_IN_MILLIS)
        )
      )
      .defaultHeader(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
      .build()
  }

  @Bean
  fun pokeApiFactory(pokeWebClient: WebClient): HttpServiceProxyFactory =
    HttpServiceProxyFactory.builderFor(WebClientAdapter.create(pokeWebClient)).build()

  @Bean
  fun berryApiClient(factory: HttpServiceProxyFactory) = factory.createClient<BerryApiClient>()

  @Bean
  fun contestApiClient(factory: HttpServiceProxyFactory) = factory.createClient<ContestApiClient>()

  @Bean
  fun encounterApiClient(factory: HttpServiceProxyFactory) = factory.createClient<EncounterApiClient>()

  @Bean
  fun evolutionApiClient(factory: HttpServiceProxyFactory) = factory.createClient<EvolutionApiClient>()

  @Bean
  fun gameApiClient(factory: HttpServiceProxyFactory) = factory.createClient<GameApiClient>()

  @Bean
  fun itemApiClient(factory: HttpServiceProxyFactory) = factory.createClient<ItemApiClient>()

  @Bean
  fun locationApiClient(factory: HttpServiceProxyFactory) = factory.createClient<LocationApiClient>()

  @Bean
  fun machineApiClient(factory: HttpServiceProxyFactory) = factory.createClient<MachineApiClient>()

  @Bean
  fun moveApiClient(factory: HttpServiceProxyFactory) = factory.createClient<MoveApiClient>()

  @Bean
  fun pokemonApiClient(factory: HttpServiceProxyFactory) = factory.createClient<PokemonApiClient>()

  @Bean
  fun utilityApiClient(factory: HttpServiceProxyFactory) = factory.createClient<UtilityApiClient>()

  companion object {
    private const val CONNECT_TIMEOUT_IN_MILLIS = 5000
    private const val RESPONSE_TIMEOUT_SECONDS = 20L
  }
}
