package org.freekode.cryptobot.priceservice.domain.indicator

import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.freekode.cryptobot.priceservice.domain.alert.CheckAlertWithIndicatorRequest
import java.time.LocalDateTime


data class GetLatestIndicatorValueInRangeRequest(
    val name: IndicatorName,
    val pair: MarketPair,
    val fromDate: LocalDateTime,
    val toDate: LocalDateTime
)
