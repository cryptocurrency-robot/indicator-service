package org.freekode.cryptobot.indicatorservice.domain.alert


interface AlertRepository {
    fun addAlert(request: AddAlertRequest): Alert

    fun getAlerts(ids: Set<Int>): Set<Alert>

    fun getAlerts(request: GetAlertsRequest): Set<Alert>

    fun removeAlert(id: Int)
}
