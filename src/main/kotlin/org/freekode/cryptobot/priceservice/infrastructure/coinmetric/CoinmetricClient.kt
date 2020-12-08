package org.freekode.cryptobot.priceservice.infrastructure.coinmetric

import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorName
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorValue
import org.freekode.cryptobot.priceservice.domain.indicator.Indicators
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
            .map { getIndicatorValue(Indicators.ISSUANCE, it) }
            .toList()
    }

    private fun getIndicatorValue(indicatorName: IndicatorName, it: CoinmetricIndicatorValueDTO) =
        IndicatorValue(
            indicatorName,
            MarketPair.BTC,
            LocalDateTime.parse(it.time, dateTimeFormatter),
            BigDecimal.valueOf(it.value.toDouble())
        )

    private fun buildUri(uriBuilder: UriBuilder, pair: MarketPair, metric: String): URI {
        return uriBuilder
            .path("/v4/timeseries/asset-metrics")
            .queryParam("assets", pair.getStringValue().toLowerCase())
            .queryParam("metrics", metric)
            .queryParam("page_size", "10000")
            .build()
    }
}
