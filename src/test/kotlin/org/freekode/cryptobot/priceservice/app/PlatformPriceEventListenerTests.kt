package org.freekode.cryptobot.priceservice.app

import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.freekode.cryptobot.priceservice.domain.price.PlatformName
import org.freekode.cryptobot.priceservice.domain.price.PlatformValueEvent
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jms.core.JmsTemplate
import java.math.BigDecimal


@SpringBootTest
class PlatformPriceEventListenerTests {

    @Value("\${event.topic.platformValue}")
    private var platformPriceTopic: String? = null

    @Autowired
    private var jmsTemplate: JmsTemplate? = null

    @Autowired
    private var platformValueEventListener: PlatformValueEventListener? = null

    @Test
    fun testAlertProvidedValue() {
        jmsTemplate!!.convertAndSend(platformPriceTopic!!, PlatformValueEvent(PlatformName("test"), MarketPair.BTC_USDT, BigDecimal(10), 10))
        Thread.sleep(200)
    }
}
