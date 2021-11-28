package org.freekode.cryptobot.indicatorservice.domain.alert

import org.freekode.cryptobot.indicatorservice.domain.MarketPair
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorId
import java.math.BigDecimal


data class AddAlertRequest(
    val indicatorId: IndicatorId,
    val pair: MarketPair,
    val type: AlertType,
    val value: BigDecimal,
)
