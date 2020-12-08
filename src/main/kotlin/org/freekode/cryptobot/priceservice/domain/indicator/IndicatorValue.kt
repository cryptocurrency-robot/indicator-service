package org.freekode.cryptobot.priceservice.domain.indicator

import org.freekode.cryptobot.priceservice.domain.MarketPair
import java.math.BigDecimal
import java.time.LocalDateTime


data class IndicatorValue(
    val name: IndicatorName,
    val pair: MarketPair,
    val date: LocalDateTime,
    val value: BigDecimal
)
