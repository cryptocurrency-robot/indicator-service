package org.freekode.cryptobot.indicatorservice.app.platform

import io.github.resilience4j.ratelimiter.RateLimiter
import io.github.resilience4j.ratelimiter.RateLimiterConfig
import io.github.resilience4j.ratelimiter.RateLimiterRegistry
import org.freekode.cryptobot.indicatorservice.app.alert.AlertService
import org.freekode.cryptobot.indicatorservice.domain.alert.AlertTriggeredEventSender
import org.freekode.cryptobot.indicatorservice.domain.port.platform.PlatformEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.function.Consumer

@Service
class PlatformEventListener(
    private val alertService: AlertService,
    private val alertTriggeredEventSender: AlertTriggeredEventSender
) {

    private val log: Logger = LoggerFactory.getLogger(PlatformEventListener::class.java)

    private val rateLimitedOnPlatformPrice: Consumer<PlatformEvent>

    private val rateLimiter: RateLimiter

    init {
        val rateLimiterConfig = getRateLimiterConfig()
        val rateLimiterRegistry = RateLimiterRegistry.of(rateLimiterConfig)
        rateLimiter = rateLimiterRegistry.rateLimiter("main")
        rateLimitedOnPlatformPrice = RateLimiter.decorateConsumer(rateLimiter) { rateLimited(it) }
    }

    //    @JmsListener(destination = "\${event.topic.platform}")
    fun onPlatformPrice(platformEvent: PlatformEvent) {
        rateLimitedOnPlatformPrice.accept(platformEvent)
    }

    private fun rateLimited(event: PlatformEvent) {
        log.info("platform event = ${event.value}")
        sendTriggeredAlerts(event)
    }

    private fun sendTriggeredAlerts(event: PlatformEvent) {
//        alertService.checkAndRemoveAlerts(CheckAlertWithValueRequest(Indicators.PRICE, event.pair, event.value))
//            .forEach {
//                log.info("Triggered! $it")
//                alertTriggeredEventSender.send(AlertTriggeredEvent(it))
//            }
    }

    private fun getRateLimiterConfig() = RateLimiterConfig.custom()
        .limitRefreshPeriod(Duration.ofSeconds(1))
        .limitForPeriod(1)
        .build()
}
