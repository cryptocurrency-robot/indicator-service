package org.freekode.cryptobot.indicatorservice.app.indicator

import org.freekode.cryptobot.indicatorservice.app.alert.AlertService
import org.freekode.cryptobot.indicatorservice.domain.MarketPair
import org.freekode.cryptobot.indicatorservice.domain.alert.TestAlertWithValueRequest
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorEvent
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorId
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class IndicatorEventListener(
    private val alertService: AlertService
) {

    @JmsListener(destination = "\${event.topic.indicator}")
    fun onPlatformPrice(event: IndicatorEvent) {
        println(event)

        val indicatorId = IndicatorId(event.indicatorId)
        val pair = MarketPair.valueOf(event.pair)
        val value = BigDecimal(event.newValue)

        val request = TestAlertWithValueRequest(indicatorId, pair, value)

        alertService.testAndRemoveAlerts(request)
    }
}
