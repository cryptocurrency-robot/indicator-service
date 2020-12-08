package org.freekode.cryptobot.priceservice.infrastructure.indicator

import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorExternalRepository
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorName
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorValue
import org.freekode.cryptobot.priceservice.domain.indicator.Indicators
import org.freekode.cryptobot.priceservice.infrastructure.lookintobitcoin.LookintobitcoinClient
import org.springframework.stereotype.Repository


@Repository
class NUPLExternalRepository(
    private val lookintobitcoinClient: LookintobitcoinClient
) : IndicatorExternalRepository {
    override fun getIndicatorName(): IndicatorName {
        return Indicators.NUPL
    }

    override fun getIndicatorValues(pair: MarketPair): List<IndicatorValue> {
        return lookintobitcoinClient.getNUPL(pair)
    }

}
