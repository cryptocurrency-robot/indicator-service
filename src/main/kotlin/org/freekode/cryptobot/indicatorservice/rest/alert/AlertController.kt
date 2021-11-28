package org.freekode.cryptobot.indicatorservice.rest.alert

import org.freekode.cryptobot.indicatorservice.app.alert.AlertService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("alert")
class AlertController(private val alertService: AlertService) {
    @PostMapping
    fun addAlert(@RequestBody requestDTO: AddAlertRequestDTO): AlertDTO {
        val alert = alertService.addAlert(requestDTO.toDomain())
        return AlertDTO(alert)
    }

    @GetMapping
    fun getAlerts(@RequestParam alertIds: Set<Int>): Set<AlertDTO> {
        return alertService.getAlerts(alertIds)
            .map { AlertDTO(it) }
            .toSet()
    }

    @DeleteMapping("/{id}")
    fun removeAlert(@PathVariable id: Int) {
        return alertService.removeAlert(id)
    }
}
