package org.freekode.cryptobot.priceservice.domain.alert.checker

import org.freekode.cryptobot.priceservice.domain.alert.AlertType
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class AlertCheckerLessThan : AlertChecker {
    override fun getType(): AlertType = AlertType.LESS_THAN

    override fun check(alertValue: BigDecimal, currentValue: BigDecimal): Boolean {
        return currentValue < alertValue
    }
}
