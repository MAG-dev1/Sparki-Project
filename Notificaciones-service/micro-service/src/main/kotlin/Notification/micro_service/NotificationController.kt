package Notification.micro_service

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.stereotype.Controller
import org.springframework.http.ResponseEntity

@Controller
@RequestMapping("/notifications")
class NotificationController {

    @GetMapping("/getAll")
    fun getNotifications(): ResponseEntity<String>? {
        return ResponseEntity.ok("Hello World")
    }
}