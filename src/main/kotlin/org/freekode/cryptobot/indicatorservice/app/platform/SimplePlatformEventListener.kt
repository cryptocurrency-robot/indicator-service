package org.freekode.cryptobot.indicatorservice.app.platform

import org.freekode.cryptobot.indicatorservice.app.indicator.IndicatorService
import org.freekode.cryptobot.indicatorservice.domain.MarketPair
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorId
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorValue
import org.freekode.cryptobot.indicatorservice.domain.port.platform.PlatformEvent
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Service
class SimplePlatformEventListener(
    private val indicatorService: IndicatorService
) {

    @JmsListener(destination = "\${event.topic.platform}")
    fun onPlatformPrice(platformEvent: PlatformEvent) {
        saveIndicator(platformEvent)
    }

    private fun saveIndicator(platformEvent: PlatformEvent) {
        val indicatorValue = getIndicatorValue(platformEvent)
        indicatorService.updateIndicator(indicatorValue)
    }

    private fun getIndicatorValue(platformEvent: PlatformEvent) =
        IndicatorValue(
            IndicatorId(platformEvent.indicatorId),
            MarketPair.valueOf(platformEvent.pair),
            BigDecimal(platformEvent.value),
            LocalDateTime.ofInstant(Instant.ofEpochSecond(platformEvent.timestamp), TimeZone.getDefault().toZoneId())
        )
}
