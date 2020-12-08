package org.freekode.cryptobot.priceservice.infrastructure.indicator

import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorExternalRepository
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorName
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorValue
import org.freekode.cryptobot.priceservice.domain.indicator.Indicators
import org.freekode.cryptobot.priceservice.infrastructure.coinmetric.CoinmetricClient
import org.springframework.stereotype.Repository


@Repository
class IssuanceExternalRepository(
    private val coinmetricClient: CoinmetricClient
) : IndicatorExternalRepository {
    override fun getIndicatorName(): IndicatorName {
        return Indicators.ISSUANCE
    }

    override fun getIndicatorValues(pair: MarketPair): List<IndicatorValue> {
        return coinmetricClient.getIssuance(pair)
    }

}
