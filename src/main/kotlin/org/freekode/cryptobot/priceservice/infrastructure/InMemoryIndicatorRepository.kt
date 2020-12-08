package org.freekode.cryptobot.priceservice.infrastructure

import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.freekode.cryptobot.priceservice.domain.indicator.*
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.BiFunction
import kotlin.collections.HashMap

@Service
class InMemoryIndicatorRepository(
    private val indicatorExternalRepositoryRegistry: IndicatorExternalRepositoryRegistry
) : IndicatorRepository {
    private val storage: MutableMap<IndicatorName, MutableList<IndicatorValue>> = HashMap()

    override fun addIndicatorValue(indicatorValue: IndicatorValue) {
        storage.compute(indicatorValue.name, BiFunction { _, value ->
            if (value == null) {
                return@BiFunction mutableListOf(indicatorValue)
            } else {
                value.add(indicatorValue)
                return@BiFunction value
            }
        })
    }

    override fun getIndicatorValues(request: GetAllIndicatorValuesRequest): List<IndicatorValue> {
        val values = storage.getOrDefault(request.name, listOf())
            .filter { it.pair == request.pair }
            .toList()
        if (values.isEmpty()) {
            return fillFromExternalRepository(request.name, request.pair)
        }
        return values
    }

    override fun getIndicatorValues(request: GetIndicatorValuesInRangeRequest): List<IndicatorValue> {
        val values = getIndicatorValues(GetAllIndicatorValuesRequest(request))
            .filter { it.date >= request.fromDate && it.date <= request.toDate }
            .filter { it.pair == request.pair }
            .toList()
        if (values.isEmpty()) {
            return fillFromExternalRepository(request.name, request.pair)
        }
        return values
    }

    override fun getIndicatorValue(request: GetLatestIndicatorValueInRangeRequest): IndicatorValue? {
        return getIndicatorValues(GetIndicatorValuesInRangeRequest(request))
            .maxByOrNull { it.date }
    }

    private fun fillFromExternalRepository(indicatorName: IndicatorName, pair: MarketPair): List<IndicatorValue> {
        val externalRepository = indicatorExternalRepositoryRegistry.getExternalRepository(indicatorName)
        return externalRepository.getIndicatorValues(pair)
            .onEach { addIndicatorValue(it) }
    }

}
