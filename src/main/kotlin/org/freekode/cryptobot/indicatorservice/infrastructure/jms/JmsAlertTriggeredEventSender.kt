package org.freekode.cryptobot.indicatorservice.infrastructure.jms

import org.freekode.cryptobot.indicatorservice.domain.alert.AlertTriggeredEvent
import org.freekode.cryptobot.indicatorservice.domain.alert.AlertTriggeredEventSender
import org.springframework.beans.factory.annotation.Value
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Service

@Service
class JmsAlertTriggeredEventSender(
    @Value("\${event.topic.alert-triggered}") private val topic: String,
    private val jmsTemplate: JmsTemplate,
) : AlertTriggeredEventSender {

    override fun send(event: AlertTriggeredEvent) {
        jmsTemplate.convertAndSend(topic, event)
    }

}
