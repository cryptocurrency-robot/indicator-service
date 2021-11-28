package org.freekode.cryptobot.indicatorservice.rest.alert

import org.freekode.cryptobot.indicatorservice.domain.MarketPair
import org.freekode.cryptobot.indicatorservice.domain.alert.Alert
import org.freekode.cryptobot.indicatorservice.domain.alert.AlertType
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorId
import java.math.BigDecimal


data class AlertDTO(
    val id: Int,
    val indicatorId: IndicatorId,
    val pair: MarketPair,
    val type: AlertType,
    val value: BigDecimal,
) {
    constructor(alert: Alert) : this(alert.id, alert.indicatorId, alert.pair, alert.type, alert.value)
}
