package org.freekode.cryptobot.priceservice.domain.price

import org.freekode.cryptobot.priceservice.domain.MarketPair
import java.io.Serializable
import java.math.BigDecimal


data class PlatformPriceEvent(
    val platformName: PlatformName,
    val pair: MarketPair,
    val price: BigDecimal,
    val timestamp: Long
) : Serializable
