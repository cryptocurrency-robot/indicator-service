package org.freekode.cryptobot.priceservice.domain.alert


interface AlertTriggeredEventSender {
    fun send(event: AlertTriggeredEvent)
}
