package org.freekode.cryptobot.priceservice.domain.alert

import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorName
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorValue
import java.math.BigDecimal


data class CheckAlertWithValueRequest(
    val indicatorName: IndicatorName,
    val pair: MarketPair,
    val value: BigDecimal
) {
    constructor(indicatorValue: IndicatorValue) : this(indicatorValue.name, indicatorValue.pair, indicatorValue.value)
}
