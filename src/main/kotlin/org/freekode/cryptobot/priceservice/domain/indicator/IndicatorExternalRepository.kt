package org.freekode.cryptobot.priceservice.domain.indicator

import org.freekode.cryptobot.priceservice.domain.MarketPair


interface IndicatorExternalRepository {
    fun getIndicatorName(): IndicatorName

    fun getIndicatorValues(pair: MarketPair): List<IndicatorValue>
}
