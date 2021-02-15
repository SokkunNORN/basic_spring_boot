package kh.org.nbc.actuator_service.controller

import kh.org.nbc.actuator_service.fast_health_check.FastHealthCheckService
import kh.org.nbc.actuator_service.fast_health_check.dto.StatusDTO
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/v1/fastHealth"])
class FastHealthCheckController (
        private val fastHealthCheckService: FastHealthCheckService
) {

    @GetMapping("/bredCM")
    fun checkCMStatus () : ResponseEntity<StatusDTO> {
        try {
            return fastHealthCheckService
                .checkFastTransferService("http://192.168.0.74:9100/actuator/health")
                .apply {
                this.body.apply {
                    this?.status = "FAST TRANSFER SERVICE STATUS IS ${this?.status}"
                }
            }
        } catch (ex: Exception) {
            return ResponseEntity
                .ok(StatusDTO(status = "FAST TRANSFER SERVICE STATUS IS DOWN DUE TO ${ex.localizedMessage}"))
        }
    }

    @GetMapping("/auth")
    fun checkHealthAuth () : ResponseEntity<StatusDTO> {
        try {
            return fastHealthCheckService
                .checkFastTransferService("https://web-fast-dev.sorakh.app/core/auth/actuator/health")
                .apply {
                    this.body.apply {
                        this?.status = "FAST AUTH SERVICE STATUS IS ${this?.status}"
                    }
                }
        } catch (ex: Exception) {
            return ResponseEntity.ok(StatusDTO(status = "FAST AUTH SERVICE STATUS IS DOWN DUE TO ${ex.localizedMessage}"))
        }
    }

    @GetMapping("/flexcube")
    fun checkHealthFlexcube () : ResponseEntity<StatusDTO> {
        try {
            return fastHealthCheckService
                .checkFastTransferService("https://web-fast-dev.sorakh.app/core/flexcube/actuator/health")
                .apply {
                    this.body.apply {
                        this?.status = "FAST FLEXCUBE SERVICE STATUS IS ${this?.status}"
                    }
                }
        } catch (ex: Exception) {
            return ResponseEntity.ok(StatusDTO(status = "FAST FLEXCUTE SERVICE STATUS IS DOWN DUE TO ${ex.localizedMessage}"))
        }
    }
}