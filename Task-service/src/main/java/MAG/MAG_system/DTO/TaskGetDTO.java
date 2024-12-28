package MAG.MAG_system.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

import MAG.MAG_system.Model.TaskType;
import lombok.Builder;

@Builder
public record TaskGetDTO(

    String name,
    String description,
    TaskType type,
    Integer score,
    LocalDateTime created_date,
    LocalDateTime  expired_date,
    String subject

) {

}
