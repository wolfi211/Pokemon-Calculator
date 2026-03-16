package hu.danielwolf.pokeCounter.external.config

import hu.danielwolf.pokeCounter.external.api.berries.BerryApi
import hu.danielwolf.pokeCounter.external.api.contests.ContestApi
import hu.danielwolf.pokeCounter.external.api.encounters.EncounterApi
import hu.danielwolf.pokeCounter.external.api.evolution.EvolutionApi
import hu.danielwolf.pokeCounter.external.api.games.GameApi
import hu.danielwolf.pokeCounter.external.api.items.ItemApi
import hu.danielwolf.pokeCounter.external.api.locations.LocationApi
import hu.danielwolf.pokeCounter.external.api.machines.MachineApi
import hu.danielwolf.pokeCounter.external.api.move.MoveApi
import hu.danielwolf.pokeCounter.external.api.pokemon.PokemonApi
import hu.danielwolf.pokeCounter.external.api.utilities.UtilityApi
import io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS
import java.time.Duration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import org.springframework.web.service.invoker.createClient
import reactor.netty.http.client.HttpClient

@Configuration
class WebclientConfig(private val pokeApiProperties: PokeApiProperties) {

  @Bean
  fun pokeWebClient(): WebClient {
    return WebClient.builder()
      .baseUrl(pokeApiProperties.baseUrl)
      .exchangeStrategies(ExchangeStrategies.builder().codecs { configurer ->
        configurer.defaultCodecs().maxInMemorySize(10 * 1024 * 1024)
      }.build())
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
  fun berryApi(factory: HttpServiceProxyFactory) = factory.createClient<BerryApi>()

  @Bean
  fun contestApi(factory: HttpServiceProxyFactory) = factory.createClient<ContestApi>()

  @Bean
  fun encounterApi(factory: HttpServiceProxyFactory) = factory.createClient<EncounterApi>()

  @Bean
  fun evolutionApi(factory: HttpServiceProxyFactory) = factory.createClient<EvolutionApi>()

  @Bean
  fun gameApi(factory: HttpServiceProxyFactory) = factory.createClient<GameApi>()

  @Bean
  fun itemApi(factory: HttpServiceProxyFactory) = factory.createClient<ItemApi>()

  @Bean
  fun locationApi(factory: HttpServiceProxyFactory) = factory.createClient<LocationApi>()

  @Bean
  fun machineApi(factory: HttpServiceProxyFactory) = factory.createClient<MachineApi>()

  @Bean
  fun moveApi(factory: HttpServiceProxyFactory) = factory.createClient<MoveApi>()

  @Bean
  fun pokemonApi(factory: HttpServiceProxyFactory) = factory.createClient<PokemonApi>()

  @Bean
  fun utilityApi(factory: HttpServiceProxyFactory) = factory.createClient<UtilityApi>()

  companion object {
    private const val CONNECT_TIMEOUT_IN_MILLIS = 5000
    private const val RESPONSE_TIMEOUT_SECONDS = 20L
  }
}
