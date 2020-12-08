package org.freekode.cryptobot.priceservice.rest

import java.time.LocalDateTime


data class IndicatorDTO(
    val date: LocalDateTime,
    val value: Double
)
