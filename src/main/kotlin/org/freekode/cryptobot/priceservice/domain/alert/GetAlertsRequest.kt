package org.freekode.cryptobot.priceservice.domain.alert

import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorName

data class GetAlertsRequest(
    val indicatorName: IndicatorName,
    val pair: MarketPair
) {
    constructor(request: CheckAlertWithValueRequest) : this(request.indicatorName, request.pair)
}
