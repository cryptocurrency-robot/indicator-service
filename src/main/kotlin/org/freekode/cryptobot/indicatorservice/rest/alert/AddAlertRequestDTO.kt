package org.freekode.cryptobot.indicatorservice.rest.alert

import org.freekode.cryptobot.indicatorservice.domain.MarketPair
import org.freekode.cryptobot.indicatorservice.domain.alert.AddAlertRequest
import org.freekode.cryptobot.indicatorservice.domain.alert.AlertType
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorId
import java.math.BigDecimal


data class AddAlertRequestDTO(
    val indicatorId: IndicatorId,
    val pair: MarketPair,
    val type: AlertType,
    val value: BigDecimal,
) {
    fun toDomain(): AddAlertRequest {
        return AddAlertRequest(indicatorId, pair, type, value)
    }
}
