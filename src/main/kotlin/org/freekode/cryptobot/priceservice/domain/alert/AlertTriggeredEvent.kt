package org.freekode.cryptobot.priceservice.domain.alert

import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorName
import java.io.Serializable
import java.math.BigDecimal


data class AlertTriggeredEvent(
    val id: Int,
    val indicatorName: IndicatorName,
    val pair: MarketPair,
    val type: AlertType,
    val value: BigDecimal,
) : Serializable {
    constructor(alert: Alert) : this(alert.id, alert.indicatorName, alert.pair, alert.type, alert.value)
}
