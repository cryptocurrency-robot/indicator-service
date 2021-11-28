package org.freekode.cryptobot.indicatorservice.domain.indicator

import org.freekode.cryptobot.indicatorservice.domain.MarketPair
import java.time.LocalDateTime


data class GetLatestIndicatorValueInRangeRequest(
    val name: IndicatorId,
    val pair: MarketPair,
    val fromDate: LocalDateTime,
    val toDate: LocalDateTime
)
