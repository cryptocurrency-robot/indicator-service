package org.freekode.cryptobot.priceservice.app

import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.freekode.cryptobot.priceservice.domain.indicator.GetAllIndicatorValuesRequest
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorRepository
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorValue
import org.freekode.cryptobot.priceservice.domain.indicator.Indicators
import org.springframework.stereotype.Service


@Service
class IndicatorService(
    private val indicatorRepository: IndicatorRepository
) {
    fun getCoinIssuance(pair: MarketPair): List<IndicatorValue> {
        return indicatorRepository.getIndicatorValues(GetAllIndicatorValuesRequest(Indicators.ISSUANCE, pair))
    }

    fun getNUPL(pair: MarketPair): List<IndicatorValue> {
        return indicatorRepository.getIndicatorValues(GetAllIndicatorValuesRequest(Indicators.NUPL, pair))
    }

    fun getPuellMultiple(pair: MarketPair): List<IndicatorValue> {
        return indicatorRepository.getIndicatorValues(GetAllIndicatorValuesRequest(Indicators.PUELL_MULTIPLE, pair))
    }

}
