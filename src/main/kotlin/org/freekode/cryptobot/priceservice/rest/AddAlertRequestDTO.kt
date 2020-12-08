package org.freekode.cryptobot.priceservice.rest

import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.freekode.cryptobot.priceservice.domain.alert.AddAlertRequest
import org.freekode.cryptobot.priceservice.domain.alert.AlertType
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorName
import java.math.BigDecimal


data class AddAlertRequestDTO(
    val indicatorName: IndicatorName,
    val pair: MarketPair,
    val type: AlertType,
    val value: BigDecimal,
) {
    fun toDomain(): AddAlertRequest {
        return AddAlertRequest(indicatorName, pair, type, value)
    }
}
