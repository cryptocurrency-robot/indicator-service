package org.freekode.cryptobot.indicatorservice.infrastructure.coinmetric

import org.freekode.cryptobot.indicatorservice.domain.MarketPair
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorId
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorValue
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriBuilder
import java.math.BigDecimal
import java.net.URI
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// TODO must be extracted to external service

@Service
class CoinmetricClient {
    private final val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'")

    private final val exchangeStrategies = ExchangeStrategies.builder()
        .codecs { it.defaultCodecs().maxInMemorySize(1024 * 1000) }
        .build()

    private val webClient = WebClient
        .builder()
        .exchangeStrategies(exchangeStrategies)
        .baseUrl("https://community-api.coinmetrics.io/")
        .build()

    fun getIssuance(pair: MarketPair): List<IndicatorValue> {
        return webClient
            .get()
            .uri { buildUri(it, pair, "IssTotNtv") }
            .retrieve()
            .bodyToMono(CoinmetricResponseDTO::class.java)
            .map { it.data }
            .block()!!
            .map { getIndicatorValue(it) }
            .toList()
    }

    private fun getIndicatorValue(it: CoinmetricIndicatorValueDTO) =
        IndicatorValue(
            IndicatorId("!!!!!!!!"),
            MarketPair.BTC,
            BigDecimal.valueOf(it.value.toDouble()),
            LocalDateTime.parse(it.time, dateTimeFormatter)
        )

    private fun buildUri(uriBuilder: UriBuilder, pair: MarketPair, metric: String): URI {
        return uriBuilder
            .path("/v4/timeseries/asset-metrics")
            .queryParam("assets", pair.getStringValue().lowercase())
            .queryParam("metrics", metric)
            .queryParam("page_size", "10000")
            .build()
    }
}
