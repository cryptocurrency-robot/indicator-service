package org.freekode.cryptobot.indicatorservice.domain.indicator

interface IndicatorEventSender {
    fun send(event: IndicatorEvent)
}
