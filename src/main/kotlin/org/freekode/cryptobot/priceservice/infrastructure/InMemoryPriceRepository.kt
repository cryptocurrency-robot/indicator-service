package org.freekode.cryptobot.priceservice.infrastructure

import org.freekode.cryptobot.priceservice.domain.price.PriceRepository
import org.freekode.cryptobot.priceservice.domain.price.PlatformValueEvent
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class InMemoryPriceRepository : PriceRepository {
    private val storage: MutableMap<Long, PlatformValueEvent> = HashMap()

    override fun addPrice(platformValueEvent: PlatformValueEvent) {
        storage[platformValueEvent.timestamp] = platformValueEvent
    }

    override fun getPrices(startDate: LocalDateTime, endDate: LocalDateTime): List<PlatformValueEvent> {
        return ArrayList()
    }
}
