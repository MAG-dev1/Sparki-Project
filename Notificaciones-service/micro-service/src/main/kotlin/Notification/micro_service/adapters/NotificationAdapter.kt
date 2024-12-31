package Notification.micro_service.adapters

import Notification.micro_service.Notification
import Notification.micro_service.NotificationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.stereotype.Controller
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
@RequestMapping("/notifications")
class NotificationAdapter(private val notificationService:NotificationService) {

    @GetMapping("/getAll")
    fun getNotifications(): ResponseEntity<*>? {
        val response = notificationService.getAllNotifications()
        return ResponseEntity.ok(response)
    }

    @PostMapping("")
    fun createNotification(@RequestBody request: Notification): ResponseEntity<*>? {
        val response = notificationService.createNotification(request)
        return ResponseEntity.ok(response)
    }
}