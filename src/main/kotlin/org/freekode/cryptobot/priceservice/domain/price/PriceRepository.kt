package org.freekode.cryptobot.priceservice.domain.price

import java.time.LocalDateTime


interface PriceRepository {
    fun addPrice(platformPriceEvent: PlatformPriceEvent)

    fun getPrices(startDate: LocalDateTime, endDate: LocalDateTime): List<PlatformPriceEvent>
}
