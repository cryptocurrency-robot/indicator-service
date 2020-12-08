package org.freekode.cryptobot.priceservice.infrastructure

import org.freekode.cryptobot.priceservice.domain.price.PriceRepository
import org.freekode.cryptobot.priceservice.domain.price.PlatformPriceEvent
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class InMemoryPriceRepository : PriceRepository {
    private val storage: MutableMap<Long, PlatformPriceEvent> = HashMap()

    override fun addPrice(platformPriceEvent: PlatformPriceEvent) {
        storage[platformPriceEvent.timestamp] = platformPriceEvent
    }

    override fun getPrices(startDate: LocalDateTime, endDate: LocalDateTime): List<PlatformPriceEvent> {
        return ArrayList()
    }
}
