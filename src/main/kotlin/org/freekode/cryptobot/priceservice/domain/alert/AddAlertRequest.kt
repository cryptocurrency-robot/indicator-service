package org.freekode.cryptobot.priceservice.domain.alert

import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorName
import java.math.BigDecimal


data class AddAlertRequest(
    val indicatorName: IndicatorName,
    val pair: MarketPair,
    val type: AlertType,
    val value: BigDecimal,
)
