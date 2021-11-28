package org.freekode.cryptobot.indicatorservice.domain.alert

import org.freekode.cryptobot.indicatorservice.domain.MarketPair
import org.freekode.cryptobot.indicatorservice.domain.alert.checker.AlertChecker
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorId
import java.math.BigDecimal


class Alert(
    val id: Int,
    val indicatorId: IndicatorId,
    val pair: MarketPair,
    val type: AlertType,
    val value: BigDecimal,
    private val checker: AlertChecker
) {
    fun test(request: TestAlertWithValueRequest): Boolean {
        if (request.indicatorId != indicatorId) {
            throw RuntimeException("Request has different indicator")
        }
        if (request.pair != pair) {
            throw RuntimeException("Request has different pair")
        }
        return checker.check(value, request.value)
    }

}
