package org.freekode.cryptobot.indicatorservice.domain.indicator

import org.freekode.cryptobot.indicatorservice.domain.MarketPair


data class GetLatestIndicatorValueRequest(
    val indicatorId: IndicatorId,
    val pair: MarketPair
)
