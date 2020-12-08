package org.freekode.cryptobot.priceservice.infrastructure

import org.freekode.cryptobot.priceservice.domain.alert.AlertTriggeredEvent
import org.freekode.cryptobot.priceservice.domain.alert.AlertTriggeredEventSender
import org.springframework.beans.factory.annotation.Value
import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Service

@Service
class JmsEventEventSender(
    @Value("\${event.topic.alertTriggered}") private val alertTriggeredTopic: String,
    private val jmsTemplate: JmsTemplate,
) : AlertTriggeredEventSender {

    override fun send(event: AlertTriggeredEvent) {
        jmsTemplate.convertAndSend(alertTriggeredTopic, event)
    }

}
