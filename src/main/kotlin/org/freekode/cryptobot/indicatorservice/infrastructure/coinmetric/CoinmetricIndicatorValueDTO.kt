package org.freekode.cryptobot.indicatorservice.infrastructure.coinmetric

import com.fasterxml.jackson.annotation.JsonAlias


data class CoinmetricIndicatorValueDTO(
    val asset: String,
    val time: String,
    @JsonAlias(value = ["PriceUSD", "IssTotNtv"])
    val value: String
)
