package org.freekode.cryptobot.priceservice.app

import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.freekode.cryptobot.priceservice.domain.alert.AddAlertRequest
import org.freekode.cryptobot.priceservice.domain.alert.AlertType
import org.freekode.cryptobot.priceservice.domain.alert.CheckAlertWithIndicatorRequest
import org.freekode.cryptobot.priceservice.domain.alert.CheckAlertWithValueRequest
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorName
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorRepository
import org.freekode.cryptobot.priceservice.domain.indicator.IndicatorValue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.testng.Assert
import java.math.BigDecimal
import java.time.LocalDateTime


@SpringBootTest
class AlertServiceTests {

    @Autowired
    private var alertService: AlertService? = null

    @Autowired
    private var indicatorRepository: IndicatorRepository? = null

    @Test
    fun testAlertProvidedValue() {
        val indicatorName = IndicatorName("my test")
        val pair = MarketPair.BTC
        alertService!!.addAlert(AddAlertRequest(indicatorName, pair, AlertType.GREATER_THAN, BigDecimal(10)))
        alertService!!.addAlert(AddAlertRequest(indicatorName, pair, AlertType.GREATER_THAN, BigDecimal(11)))
        alertService!!.addAlert(AddAlertRequest(indicatorName, pair, AlertType.GREATER_THAN, BigDecimal(12)))

        val alerts = alertService!!.checkAndRemoveAlerts(CheckAlertWithValueRequest(indicatorName, pair, BigDecimal(20)))
        Assert.assertEquals(alerts.size, 3)
    }

    @Test
    fun testAlertIndicatorRepoValue() {
        val indicatorName = IndicatorName("my test")
        val pair = MarketPair.BTC
        alertService!!.addAlert(AddAlertRequest(indicatorName, pair, AlertType.GREATER_THAN, BigDecimal(10)))
        alertService!!.addAlert(AddAlertRequest(indicatorName, pair, AlertType.GREATER_THAN, BigDecimal(11)))
        alertService!!.addAlert(AddAlertRequest(indicatorName, pair, AlertType.GREATER_THAN, BigDecimal(12)))

        indicatorRepository!!.addIndicatorValue(IndicatorValue(indicatorName, pair, LocalDateTime.now(), BigDecimal(20)))

        val alerts = alertService!!.checkAndRemoveAlerts(CheckAlertWithIndicatorRequest(indicatorName, pair))
        Assert.assertEquals(alerts.size, 3)
    }
}
