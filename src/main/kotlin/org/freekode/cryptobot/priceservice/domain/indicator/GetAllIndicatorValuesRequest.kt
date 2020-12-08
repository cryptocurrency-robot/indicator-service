package org.freekode.cryptobot.priceservice.domain.indicator

import org.freekode.cryptobot.priceservice.domain.MarketPair


data class GetAllIndicatorValuesRequest(
    val name: IndicatorName,
    val pair: MarketPair
) {
    constructor(request: GetIndicatorValuesInRangeRequest) : this(request.name, request.pair)
}
