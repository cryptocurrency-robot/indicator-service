package org.freekode.cryptobot.indicatorservice.domain.indicator

import java.io.Serializable

data class IndicatorEvent(
    val indicatorId: String,
    val pair: String,
    val newValue: String,
    val oldValue: String?,
    val date: Long
): Serializable
