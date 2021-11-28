package org.freekode.cryptobot.indicatorservice.infrastructure.alert

import org.freekode.cryptobot.indicatorservice.domain.alert.Alert
import org.freekode.cryptobot.indicatorservice.domain.alert.checker.AlertCheckerFactory
import org.springframework.stereotype.Service


@Service
class AlertFactory(private val alertCheckerFactory: AlertCheckerFactory) {
    fun toAlert(alertAnemia: AlertAnemia): Alert {
        val checker = alertCheckerFactory.getCheckerByType(alertAnemia.type)
        return Alert(alertAnemia.id, alertAnemia.indicatorId, alertAnemia.pair, alertAnemia.type, alertAnemia.value, checker)
    }
}
