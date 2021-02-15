package kh.org.nbc.actuator_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ActuatorServiceApplication

fun main(args: Array<String>) {
	runApplication<ActuatorServiceApplication>(*args)
}
