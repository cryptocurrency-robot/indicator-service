package org.freekode.cryptobot.indicatorservice.infrastructure.alert

import org.freekode.cryptobot.indicatorservice.domain.alert.AlertType
import org.freekode.cryptobot.indicatorservice.domain.MarketPair
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorId
import java.math.BigDecimal


data class AlertAnemia(
    val id: Int,
    val indicatorId: IndicatorId,
    val pair: MarketPair,
    val type: AlertType,
    val value: BigDecimal,
)
