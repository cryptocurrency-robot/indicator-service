package org.freekode.cryptobot.priceservice.rest

import org.freekode.cryptobot.priceservice.app.IndicatorService
import org.freekode.cryptobot.priceservice.domain.Coin
import org.freekode.cryptobot.priceservice.domain.MarketPair
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("indicator")
class IndicatorController(private val indicatorService: IndicatorService) {
    @GetMapping("issuance/{coin}")
    fun getIndicator(@PathVariable coin: Coin): List<IndicatorDTO> {
        val issuance = indicatorService.getCoinIssuance(MarketPair.getOneSidePair(coin))
        return issuance
            .map { IndicatorDTO(it.date, it.value.toDouble()) }
            .sortedBy { it.date }
            .toList()
    }

    @GetMapping("nupl/{coin}")
    fun getNUPL(@PathVariable coin: Coin): List<IndicatorDTO> {
        val issuance = indicatorService.getNUPL(MarketPair.getOneSidePair(coin))
        return issuance
            .map { IndicatorDTO(it.date, it.value.toDouble()) }
            .sortedBy { it.date }
            .toList()
    }

    @GetMapping("puellMultiple/{coin}")
    fun getPuellMultiple(@PathVariable coin: Coin): List<IndicatorDTO> {
        val issuance = indicatorService.getPuellMultiple(MarketPair.getOneSidePair(coin))
        return issuance
            .map { IndicatorDTO(it.date, it.value.toDouble()) }
            .sortedBy { it.date }
            .toList()
    }

}
