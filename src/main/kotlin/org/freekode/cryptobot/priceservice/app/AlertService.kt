package org.freekode.cryptobot.priceservice.app

import org.freekode.cryptobot.priceservice.domain.alert.*
import org.freekode.cryptobot.priceservice.domain.indicator.GetLatestIndicatorValueInRangeRequest
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class AlertService(
    private val alertRepository: AlertRepository,
    private val indicatorRepository: IndicatorRepository
) {
    fun addAlert(request: AddAlertRequest): Alert {
        return alertRepository.addAlert(request)
    }

    fun getAlerts(alertIds: Set<Int>): Set<Alert> {
        return alertRepository.getAlerts(alertIds)
    }

    fun checkAndRemoveAlerts(request: CheckAlertWithValueRequest): Set<Alert> {
        return alertRepository.getAlerts(GetAlertsRequest(request))
            .filter { it.check(request) }
            .onEach { alertRepository.removeAlert(it.id) }
            .toSet()
    }

    fun checkAndRemoveAlerts(request: CheckAlertWithIndicatorRequest): Set<Alert> {
        val now = LocalDateTime.now()
        val indicatorValuesRequest = GetLatestIndicatorValueInRangeRequest(
            request.indicatorName,
            request.pair,
            startOfTheDay(now),
            endOfTheDay(now)
        )
        val indicatorValue = indicatorRepository.getIndicatorValue(indicatorValuesRequest) ?: return setOf()
        return checkAndRemoveAlerts(CheckAlertWithValueRequest(indicatorValue))
    }


    fun removeAlert(id: Int) {
        alertRepository.removeAlert(id)
    }

    private fun startOfTheDay(localDateTime: LocalDateTime): LocalDateTime {
        return localDateTime
            .withHour(0)
            .withMinute(0)
            .withSecond(0)
    }

    private fun endOfTheDay(localDateTime: LocalDateTime): LocalDateTime {
        return localDateTime
            .withHour(23)
            .withMinute(59)
            .withSecond(59)
    }
}
