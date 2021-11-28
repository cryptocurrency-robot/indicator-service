package org.freekode.cryptobot.indicatorservice.domain.indicator

import org.freekode.cryptobot.indicatorservice.domain.MarketPair


data class GetAllIndicatorValuesRequest(
    val name: IndicatorId,
    val pair: MarketPair
) {
    constructor(request: GetIndicatorValuesInRangeRequest) : this(request.name, request.pair)
}
