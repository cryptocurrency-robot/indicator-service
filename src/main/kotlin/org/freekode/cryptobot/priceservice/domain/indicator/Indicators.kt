package org.freekode.cryptobot.priceservice.domain.indicator

object Indicators {
    val PRICE = IndicatorName("Price")
    val ISSUANCE = IndicatorName("Issuance")
    val NUPL = IndicatorName("NUPL")
    val PUELL_MULTIPLE = IndicatorName("Puell Multiple")

    val DAILY_INDICATORS = setOf(ISSUANCE, NUPL, PUELL_MULTIPLE)
}
