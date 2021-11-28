package org.freekode.cryptobot.indicatorservice.app.alert

import org.freekode.cryptobot.indicatorservice.domain.alert.AddAlertRequest
import org.freekode.cryptobot.indicatorservice.domain.alert.Alert
import org.freekode.cryptobot.indicatorservice.domain.alert.AlertRepository
import org.freekode.cryptobot.indicatorservice.domain.alert.AlertTriggeredEvent
import org.freekode.cryptobot.indicatorservice.domain.alert.AlertTriggeredEventSender
import org.freekode.cryptobot.indicatorservice.domain.alert.GetAlertsRequest
import org.freekode.cryptobot.indicatorservice.domain.alert.TestAlertWithValueRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class AlertService(
    private val alertRepository: AlertRepository,
    private val alertTriggeredEventSender: AlertTriggeredEventSender
) {

    private val log: Logger = LoggerFactory.getLogger(AlertService::class.java)

    fun addAlert(request: AddAlertRequest): Alert {
        return alertRepository.addAlert(request)
    }

    fun getAlerts(alertIds: Set<Int>): Set<Alert> {
        return alertRepository.getAlerts(alertIds)
    }

    fun testAndRemoveAlerts(request: TestAlertWithValueRequest) {
        alertRepository.getAlerts(GetAlertsRequest(request.indicatorId, request.pair)).forEach {
            if (!it.test(request)) {
                return
            }

            log.info("Triggered! $it")
            alertTriggeredEventSender.send(AlertTriggeredEvent(it))
            alertRepository.removeAlert(it.id)
        }
    }

    fun removeAlert(id: Int) {
        alertRepository.removeAlert(id)
    }
}
