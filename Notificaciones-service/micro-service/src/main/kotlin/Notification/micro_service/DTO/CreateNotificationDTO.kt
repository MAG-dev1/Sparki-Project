package Notification.micro_service.DTO

import java.time.LocalDateTime

data class CreateNotificationDTO(
    val idTask: Long,
    val created_date: LocalDateTime,
    var expired_date: LocalDateTime

)
