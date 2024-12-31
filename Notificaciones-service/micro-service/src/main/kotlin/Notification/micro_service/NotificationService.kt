package Notification.micro_service

import Notification.micro_service.DTO.CreateNotificationDTO
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import Notification.micro_service.ports.NotificationRepository

@Service
class NotificationService (private val notificationRepository:NotificationRepository){
    
    fun getAllNotifications(): List<Notification> {
        return notificationRepository.findAll()
    }

    fun createNotification(notification:CreateNotificationDTO){
        
    }

}