package Notification.micro_service

import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired

@Service
class NotificationService {
    
    @Autowired
    private NotificationRepository notificationRepository;

    fun getAllNotifications(): List<Notification> {
        return listOf(Notification())
    }

}