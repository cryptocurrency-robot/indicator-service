package org.freekode.cryptobot.priceservice.domain.alert.checker

import org.freekode.cryptobot.priceservice.domain.alert.AlertType
import org.springframework.stereotype.Service


@Service
class AlertCheckerFactory(alertCheckers: List<AlertChecker>) {

    private val alertCheckerMap: Map<AlertType, AlertChecker> = alertCheckers.associateBy { it.getType() }

    fun getCheckerByType(alertType: AlertType): AlertChecker {
        return alertCheckerMap[alertType]!!
    }
}
