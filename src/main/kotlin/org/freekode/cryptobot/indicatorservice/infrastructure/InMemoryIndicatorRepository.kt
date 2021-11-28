package org.freekode.cryptobot.indicatorservice.infrastructure

import org.freekode.cryptobot.indicatorservice.domain.indicator.GetAllIndicatorValuesRequest
import org.freekode.cryptobot.indicatorservice.domain.indicator.GetIndicatorValuesInRangeRequest
import org.freekode.cryptobot.indicatorservice.domain.indicator.GetLatestIndicatorValueInRangeRequest
import org.freekode.cryptobot.indicatorservice.domain.indicator.GetLatestIndicatorValueRequest
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorId
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorRepository
import org.freekode.cryptobot.indicatorservice.domain.indicator.IndicatorValue
import org.springframework.stereotype.Repository
import java.util.function.BiFunction

@Repository
class InMemoryIndicatorRepository : IndicatorRepository {

    private val storage: MutableMap<IndicatorId, MutableList<IndicatorValue>> = HashMap()

    override fun addIndicatorValue(indicatorValue: IndicatorValue) {
        storage.compute(indicatorValue.indicatorId, BiFunction { _, value ->
            if (value == null) {
                return@BiFunction mutableListOf(indicatorValue)
            } else {
                value.add(indicatorValue)
                return@BiFunction value
            }
        })
    }

    override fun getIndicatorValue(request: GetLatestIndicatorValueRequest): IndicatorValue? {
        return getIndicatorValues(GetAllIndicatorValuesRequest(request.indicatorId, request.pair))
            .maxByOrNull { it.date }
    }

    override fun getIndicatorValues(request: GetAllIndicatorValuesRequest): List<IndicatorValue> {
        return storage.getOrDefault(request.name, listOf())
            .filter { it.pair == request.pair }
            .toList()
    }

    override fun getIndicatorValues(request: GetIndicatorValuesInRangeRequest): List<IndicatorValue> {
        return getIndicatorValues(GetAllIndicatorValuesRequest(request))
            .filter { it.date >= request.fromDate && it.date <= request.toDate }
            .filter { it.pair == request.pair }
            .toList()
    }

    override fun getIndicatorValue(request: GetLatestIndicatorValueInRangeRequest): IndicatorValue? {
        return getIndicatorValues(GetIndicatorValuesInRangeRequest(request))
            .maxByOrNull { it.date }
    }
}
