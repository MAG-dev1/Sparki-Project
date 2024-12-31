package Notification.micro_service

import org.springframework.data.annotation.Id
import jakarta.persistence.GeneratedValue
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.FutureOrPresent
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Notification (

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val idTask: Long,

    @PastOrPresent
    val created_date: LocalDateTime,

    @FutureOrPresent
    var expired_date: LocalDateTime
)