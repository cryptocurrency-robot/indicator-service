package org.freekode.cryptobot.indicatorservice.domain.indicator

import org.freekode.cryptobot.indicatorservice.domain.MarketPair
import java.time.LocalDateTime


data class GetIndicatorValuesInRangeRequest(
    val name: IndicatorId,
    val pair: MarketPair,
    val fromDate: LocalDateTime,
    val toDate: LocalDateTime
) {
    constructor(request: GetLatestIndicatorValueInRangeRequest) : this(request.name, request.pair, request.fromDate, request.toDate)
}
