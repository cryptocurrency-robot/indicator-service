package org.freekode.cryptobot.indicatorservice.domain.indicator

import org.freekode.cryptobot.indicatorservice.domain.MarketPair
import java.math.BigDecimal
import java.time.LocalDateTime


data class IndicatorValue(
    val indicatorId: IndicatorId,
    val pair: MarketPair,
    val value: BigDecimal,
    val date: LocalDateTime
) {
    fun compare(other: IndicatorValue): Int {
        assert(indicatorId == other.indicatorId)
        assert(pair == other.pair)

        return if (other.value > value) {
            1
        } else if (other.value == value) {
            0
        } else {
            -1
        }
    }
}
