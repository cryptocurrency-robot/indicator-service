package org.freekode.cryptobot.priceservice.domain.indicator

import org.springframework.stereotype.Service


@Service
class IndicatorExternalRepositoryRegistry(
    private val externalRepositories: List<IndicatorExternalRepository>
) {
    private val externalRepositoryMap: Map<IndicatorName, IndicatorExternalRepository> =
        externalRepositories.associateBy { it.getIndicatorName() }

    fun getExternalRepository(indicatorName: IndicatorName): IndicatorExternalRepository {
        return externalRepositoryMap[indicatorName]!!
    }
}
