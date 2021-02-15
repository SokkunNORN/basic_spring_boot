package kh.org.nbc.actuator_service.fast_health_check

import kh.org.nbc.actuator_service.fast_health_check.dto.StatusDTO
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.*
import org.springframework.web.client.getForEntity
import java.time.Duration
import java.util.*

@Service
class FastHealthCheckService(restTemplateBuilder: RestTemplateBuilder) {
    var restTemplate: RestTemplate = restTemplateBuilder.apply {
        this.setReadTimeout(Duration.ofSeconds(5))
        this.setConnectTimeout(Duration.ofSeconds(5))
    }.build()

    private fun getHeader(): HttpHeaders {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.accept = Collections.singletonList(MediaType.APPLICATION_JSON)

        return headers
    }

    fun checkFastTransferService(url : String) : ResponseEntity<StatusDTO>{
        return this.restTemplate.getForEntity(url, StatusDTO::class.java)
    }
}