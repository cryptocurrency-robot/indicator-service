package org.freekode.cryptobot.indicatorservice.infrastructure.jms

import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorEvent
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorEventSender
import org.springframework.beans.factory.annotation.Value
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Component

@Component
class JmsIndicatorEventSender(
    @Value("\${event.topic.indicator}") private val topic: String,
    private val jmsTemplate: JmsTemplate,
) : IndicatorEventSender {

    override fun send(event: IndicatorEvent) {
        jmsTemplate.convertAndSend(topic, event)
    }
}
