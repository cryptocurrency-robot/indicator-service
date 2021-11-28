package org.freekode.cryptobot.indicatorservice.domain.port.platform

import java.io.Serializable


data class PlatformEvent(
    val platformId: String,
    val pair: String,
    val indicatorId: String,
    val value: String,
    val timestamp: Long
) : Serializable
