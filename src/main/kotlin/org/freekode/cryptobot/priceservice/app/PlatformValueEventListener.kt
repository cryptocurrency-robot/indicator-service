package org.freekode.cryptobot.priceservice.app

import io.github.resilience4j.ratelimiter.RateLimiter
import io.github.resilience4j.ratelimiter.RateLimiterConfig
import io.github.resilience4j.ratelimiter.RateLimiterRegistry
import org.freekode.cryptobot.priceservice.domain.alert.CheckAlertWithValueRequest
import org.freekode.cryptobot.priceservice.domain.alert.AlertTriggeredEvent
import org.freekode.cryptobot.priceservice.domain.alert.AlertTriggeredEventSender
import org.freekode.cryptobot.priceservice.domain.indicator.Indicators
import org.freekode.cryptobot.priceservice.domain.price.PlatformValueEvent
import org.freekode.cryptobot.priceservice.domain.price.PriceRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.function.Consumer

@Service
class PlatformValueEventListener(
    private val priceRepository: PriceRepository,
    private val alertService: AlertService,
    private val alertTriggeredEventSender: AlertTriggeredEventSender
) {

    private val log: Logger = LoggerFactory.getLogger(PlatformValueEventListener::class.java)

    private val rateLimitedOnPlatformValue: Consumer<PlatformValueEvent>

    private val rateLimiter: RateLimiter

    init {
        val rateLimiterConfig = getRateLimiterConfig()
        val rateLimiterRegistry = RateLimiterRegistry.of(rateLimiterConfig)
        rateLimiter = rateLimiterRegistry.rateLimiter("main")
        rateLimitedOnPlatformValue = RateLimiter.decorateConsumer(rateLimiter) { rateLimited(it) }
    }

    @JmsListener(destination = "\${event.topic.platformPrice}")
    fun onPlatformPrice(platformValueEvent: PlatformValueEvent) {
        rateLimitedOnPlatformValue.accept(platformValueEvent)
    }

    private fun rateLimited(event: PlatformValueEvent) {
        log.info("platform event = ${event.value}")

        priceRepository.addPrice(event)
        sendTriggeredAlerts(event)
    }

    private fun sendTriggeredAlerts(event: PlatformValueEvent) {
        alertService.checkAndRemoveAlerts(CheckAlertWithValueRequest(Indicators.PRICE, event.indicatorName, event.value))
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
