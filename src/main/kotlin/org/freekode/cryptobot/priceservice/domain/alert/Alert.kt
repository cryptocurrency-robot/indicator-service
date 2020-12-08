package org.freekode.cryptobot.priceservice.domain.alert

import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.freekode.cryptobot.priceservice.domain.alert.checker.AlertChecker
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorName
import java.math.BigDecimal


class Alert(
    val id: Int,
    val indicatorName: IndicatorName,
    val pair: MarketPair,
    val type: AlertType,
    val value: BigDecimal,
    private val checker: AlertChecker
) {
    fun check(request: CheckAlertWithValueRequest): Boolean {
        if (request.indicatorName != indicatorName) {
            throw RuntimeException("Request has different indicator")
        }
        if (request.pair != pair) {
            throw RuntimeException("Request has different pair")
        }
        return checker.check(value, request.value)
    }

}
