package Notification.micro_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class MicroServiceApplication

fun main(args: Array<String>) {
	runApplication<MicroServiceApplication>(*args)
}
