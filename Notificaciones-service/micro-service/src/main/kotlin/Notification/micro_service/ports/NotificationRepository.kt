package Notification.micro_service.ports

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import Notification.micro_service.Notification
import java.util.*

@Repository
interface NotificationRepository : JpaRepository<Notification, Long>  