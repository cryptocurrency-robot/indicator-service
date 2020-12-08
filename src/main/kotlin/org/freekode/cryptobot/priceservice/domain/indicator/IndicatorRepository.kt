package org.freekode.cryptobot.priceservice.domain.indicator


interface IndicatorRepository {
    fun addIndicatorValue(indicatorValue: IndicatorValue)

    fun getIndicatorValues(request: GetAllIndicatorValuesRequest): List<IndicatorValue>

    fun getIndicatorValues(request: GetIndicatorValuesInRangeRequest): List<IndicatorValue>

    fun getIndicatorValue(request: GetLatestIndicatorValueInRangeRequest): IndicatorValue?
}
