package hu.danielwolf.pokeCounter.external.config

import hu.danielwolf.pokeCounter.external.api.PokeApi
import io.netty.channel.ChannelOption.CONNECT_TIMEOUT_MILLIS
import io.netty.handler.timeout.ReadTimeoutHandler
import java.time.Duration
import java.util.concurrent.TimeUnit.SECONDS
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.http.codec.json.KotlinSerializationJsonDecoder
import org.springframework.http.codec.json.KotlinSerializationJsonEncoder
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import reactor.netty.http.client.HttpClient

@Configuration
class WebclientConfig(
  private val pokeApiProperties: PokeApiProperties,
) {

  @Bean
  @Qualifier("wechatWebClient")
  fun wechatWebClient(builder: WebClient.Builder): WebClient {
    val httpClient = HttpClient.create()
      .option(CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT_SECONDS * 1000)
      .responseTimeout(Duration.ofSeconds(RESPONSE_TIMEOUT_SECONDS))
      .doOnConnected {
        it.addHandlerFirst(ReadTimeoutHandler(CONNECT_TIMEOUT_SECONDS.toLong(), SECONDS))
      }

    return builder
      .clientConnector(ReactorClientHttpConnector(httpClient))
      .codecs {
        it.defaultCodecs().apply {
          kotlinSerializationJsonDecoder(KotlinSerializationJsonDecoder(json()))
          kotlinSerializationJsonEncoder(KotlinSerializationJsonEncoder(json()))
        }
      }
      .defaultHeaders {
        it.contentType = APPLICATION_JSON
        it.accept = listOf(APPLICATION_JSON)
        it.set(HttpHeaders.USER_AGENT, "CityGo-Backend/1.0.0")
      }
      .baseUrl(pokeApiProperties.baseUrl)
      .build()
  }

  @Bean
  fun wechatOrderApi(wechatWebClient: WebClient): PokeApi =
    HttpServiceProxyFactory
      .builderFor(WebClientAdapter.create(wechatWebClient)).build()
      .createClient(PokeApi::class.java)

  companion object {
    private const val CONNECT_TIMEOUT_SECONDS = 5
    private const val RESPONSE_TIMEOUT_SECONDS = 20L
  }
}