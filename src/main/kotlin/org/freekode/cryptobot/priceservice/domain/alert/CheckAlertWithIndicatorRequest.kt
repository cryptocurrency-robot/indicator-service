package org.freekode.cryptobot.priceservice.domain.alert

import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorName
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorValue

class CheckAlertWithIndicatorRequest(
    val indicatorName: IndicatorName,
    val pair: MarketPair,
) {
}
