package org.freekode.cryptobot.indicatorservice.domain.alert

import org.freekode.cryptobot.indicatorservice.domain.MarketPair
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorId

data class GetAlertsRequest(
    val indicatorId: IndicatorId,
    val pair: MarketPair
) {
    constructor(request: TestAlertWithValueRequest) : this(request.indicatorId, request.pair)
}
