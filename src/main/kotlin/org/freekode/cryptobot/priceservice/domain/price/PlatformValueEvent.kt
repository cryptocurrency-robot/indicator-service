package org.freekode.cryptobot.priceservice.domain.price

import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorName
import java.io.Serializable
import java.math.BigDecimal


data class PlatformValueEvent(
    val platformName: PlatformName,
    val indicatorName: IndicatorName,
    val value: BigDecimal,
    val timestamp: Long
) : Serializable
