package org.freekode.cryptobot.priceservice.infrastructure.alert

import org.freekode.cryptobot.priceservice.domain.alert.AlertType
import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorName
import java.math.BigDecimal


data class AlertAnemia(
    val id: Int,
    val indicatorName: IndicatorName,
    val pair: MarketPair,
    val type: AlertType,
    val value: BigDecimal,
)
