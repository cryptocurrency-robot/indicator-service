package org.freekode.cryptobot.indicatorservice.domain.alert.checker

import org.freekode.cryptobot.indicatorservice.domain.alert.AlertType
import java.math.BigDecimal


interface AlertChecker {
    fun getType(): AlertType

    fun check(alertValue: BigDecimal, currentValue: BigDecimal): Boolean
}
