package org.freekode.cryptobot.indicatorservice.domain.alert

import org.freekode.cryptobot.indicatorservice.domain.MarketPair
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorId
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorValue
import java.math.BigDecimal


data class TestAlertWithValueRequest(
    val indicatorId: IndicatorId,
    val pair: MarketPair,
    val value: BigDecimal
) {
    constructor(indicatorValue: IndicatorValue) : this(indicatorValue.indicatorId, indicatorValue.pair, indicatorValue.value)
}
