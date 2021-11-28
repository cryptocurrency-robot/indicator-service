package org.freekode.cryptobot.indicatorservice.domain.indicator


interface IndicatorRepository {
    fun addIndicatorValue(indicatorValue: IndicatorValue)

    fun getIndicatorValue(request: GetLatestIndicatorValueRequest): IndicatorValue?

    fun getIndicatorValues(request: GetAllIndicatorValuesRequest): List<IndicatorValue>

    fun getIndicatorValues(request: GetIndicatorValuesInRangeRequest): List<IndicatorValue>

    fun getIndicatorValue(request: GetLatestIndicatorValueInRangeRequest): IndicatorValue?
}
