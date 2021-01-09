package org.freekode.cryptobot.priceservice.app

import io.github.resilience4j.ratelimiter.RateLimiter
import io.github.resilience4j.ratelimiter.RateLimiterConfig
import io.github.resilience4j.ratelimiter.RateLimiterRegistry
import org.freekode.cryptobot.priceservice.domain.alert.CheckAlertWithValueRequest
import org.freekode.cryptobot.priceservice.domain.alert.AlertTriggeredEvent
import org.freekode.cryptobot.priceservice.domain.alert.AlertTriggeredEventSender
import org.freekode.cryptobot.priceservice.domain.indicator.Indicators
import org.freekode.cryptobot.priceservice.domain.price.PlatformPriceEvent
import org.freekode.cryptobot.priceservice.domain.price.PriceRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.function.Consumer

@Service
class PlatformPriceEventListener(
    private val priceRepository: PriceRepository,
    private val alertService: AlertService,
    private val alertTriggeredEventSender: AlertTriggeredEventSender
) {

    private val log: Logger = LoggerFactory.getLogger(PlatformPriceEventListener::class.java)

    private val rateLimitedOnPlatformPrice: Consumer<PlatformPriceEvent>

    private val rateLimiter: RateLimiter

    init {
        val rateLimiterConfig = getRateLimiterConfig()
        val rateLimiterRegistry = RateLimiterRegistry.of(rateLimiterConfig)
        rateLimiter = rateLimiterRegistry.rateLimiter("main")
        rateLimitedOnPlatformPrice = RateLimiter.decorateConsumer(rateLimiter) { rateLimited(it) }
    }

    @JmsListener(destination = "\${event.topic.platformPrice}")
    fun onPlatformPrice(platformPriceEvent: PlatformPriceEvent) {
        rateLimitedOnPlatformPrice.accept(platformPriceEvent)
    }

    private fun rateLimited(event: PlatformPriceEvent) {
        log.info("platform event = ${event.price}")

        priceRepository.addPrice(event)
        sendTriggeredAlerts(event)
    }

    private fun sendTriggeredAlerts(event: PlatformPriceEvent) {
        alertService.checkAndRemoveAlerts(CheckAlertWithValueRequest(Indicators.PRICE, event.pair, event.price))
            .forEach {
                log.info("Triggered! $it")
                alertTriggeredEventSender.send(AlertTriggeredEvent(it))
            }
    }

    private fun getRateLimiterConfig() = RateLimiterConfig.custom()
        .limitRefreshPeriod(Duration.ofSeconds(1))
        .limitForPeriod(1)
        .build()
}
