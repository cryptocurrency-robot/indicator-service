package org.freekode.cryptobot.indicatorservice.app.indicator

import org.freekode.cryptobot.indicatorservice.domain.indicator.GetLatestIndicatorValueRequest
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorEvent
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorEventSender
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorRepository
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorValue
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.ZoneId

import java.time.ZonedDateTime


@Service
class IndicatorService(
    private val indicatorRepository: IndicatorRepository,
    private val indicatorEventSender: IndicatorEventSender
) {
    fun updateIndicator(newIndicatorValue: IndicatorValue) {
        val request = GetLatestIndicatorValueRequest(newIndicatorValue.indicatorId, newIndicatorValue.pair)
        val currentIndicatorValue = indicatorRepository.getIndicatorValue(request)

        if (currentIndicatorValue != null && currentIndicatorValue.compare(newIndicatorValue) == 0) {
            return
        }

        indicatorRepository.addIndicatorValue(newIndicatorValue)
        if (currentIndicatorValue == null) {
            sendEvent(newIndicatorValue, null)
        } else {
            sendEvent(newIndicatorValue, currentIndicatorValue.value)
        }
    }

    private fun sendEvent(newIndicatorValue: IndicatorValue, oldIndicatorValue: BigDecimal?) {
        val event = IndicatorEvent(
            newIndicatorValue.indicatorId.value,
            newIndicatorValue.pair.name,
            newIndicatorValue.value.toPlainString(),
            oldIndicatorValue?.toPlainString(),
            getTimestamp(LocalDateTime.now())
        )
        indicatorEventSender.send(event)
    }

    private fun getTimestamp(dateTime: LocalDateTime) =
        ZonedDateTime.of(dateTime, ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli() / 1000
}
