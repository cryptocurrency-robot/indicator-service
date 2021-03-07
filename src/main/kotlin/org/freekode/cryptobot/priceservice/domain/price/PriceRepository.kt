package org.freekode.cryptobot.priceservice.domain.price

import java.time.LocalDateTime


interface PriceRepository {
    fun addPrice(platformValueEvent: PlatformValueEvent)

    fun getPrices(startDate: LocalDateTime, endDate: LocalDateTime): List<PlatformValueEvent>
}
