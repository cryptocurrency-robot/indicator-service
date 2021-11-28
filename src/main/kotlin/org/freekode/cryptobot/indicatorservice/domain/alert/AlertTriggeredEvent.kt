package org.freekode.cryptobot.indicatorservice.domain.alert

import org.freekode.cryptobot.indicatorservice.domain.MarketPair
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorId
import java.io.Serializable
import java.math.BigDecimal


data class AlertTriggeredEvent(
    val id: Int,
    val indicatorId: IndicatorId,
    val pair: MarketPair,
    val type: AlertType,
    val value: BigDecimal,
) : Serializable {
    constructor(alert: Alert) : this(alert.id, alert.indicatorId, alert.pair, alert.type, alert.value)
}
