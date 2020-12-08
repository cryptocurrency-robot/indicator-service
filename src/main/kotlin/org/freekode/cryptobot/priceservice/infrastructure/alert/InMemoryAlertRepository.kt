package org.freekode.cryptobot.priceservice.infrastructure.alert

import org.freekode.cryptobot.priceservice.domain.alert.AddAlertRequest
import org.freekode.cryptobot.priceservice.domain.alert.Alert
import org.freekode.cryptobot.priceservice.domain.alert.AlertRepository
import org.freekode.cryptobot.priceservice.domain.alert.GetAlertsRequest
import org.springframework.stereotype.Repository


@Repository
class InMemoryAlertRepository(private val alertFactory: AlertFactory) : AlertRepository {
    private var id = 0

    private val alerts: MutableMap<Int, AlertAnemia> = HashMap()

    override fun addAlert(request: AddAlertRequest): Alert {
        val newId = id++
        alerts[newId] = AlertAnemia(newId, request.indicatorName, request.pair, request.type, request.value)
        return alertFactory.toAlert(alerts[newId]!!)
    }

    override fun getAlerts(request: GetAlertsRequest): Set<Alert> {
        return alerts
            .filter { it.value.pair == request.pair }
            .filter { it.value.indicatorName == request.indicatorName }
            .map { alertFactory.toAlert(it.value) }
            .toSet()
    }

    override fun getAlerts(ids: Set<Int>): Set<Alert> {
        return ids
            .map { alerts[id] }
            .map { alertFactory.toAlert(it!!) }
            .toSet()
    }

    override fun removeAlert(id: Int) {
        alerts.remove(id)
    }
}
