package org.freekode.cryptobot.indicatorservice.domain.alert


interface AlertTriggeredEventSender {
    fun send(event: AlertTriggeredEvent)
}
