package Notification.micro_service

import org.springframework.data.annotation.Id
import jakarta.persistence.GeneratedValue
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.FutureOrPresent

class Notification{
    @Id
    @GeneratedValue
    Long id;

    Long id_task;
    
    @PastOrPresent
    LocalDateTime created_date;

    @FutureOrPresent
    LocalDateTime expired_date;
}