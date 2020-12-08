package org.freekode.cryptobot.priceservice.rest

import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.freekode.cryptobot.priceservice.domain.alert.Alert
import org.freekode.cryptobot.priceservice.domain.alert.AlertType
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorName
import java.math.BigDecimal


data class AlertDTO(
    val id: Int,
    val indicatorName: IndicatorName,
    val pair: MarketPair,
    val type: AlertType,
    val value: BigDecimal,
) {
    constructor(alert: Alert) : this(alert.id, alert.indicatorName, alert.pair, alert.type, alert.value)
}
