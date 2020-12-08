package org.freekode.cryptobot.priceservice.domain.indicator

import org.freekode.cryptobot.priceservice.domain.MarketPair
import java.time.LocalDateTime


data class GetIndicatorValuesInRangeRequest(
    val name: IndicatorName,
    val pair: MarketPair,
    val fromDate: LocalDateTime,
    val toDate: LocalDateTime
) {
    constructor(request: GetLatestIndicatorValueInRangeRequest) : this(request.name, request.pair, request.fromDate, request.toDate)
}
