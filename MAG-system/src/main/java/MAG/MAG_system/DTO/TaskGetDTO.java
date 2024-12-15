package MAG.MAG_system.DTO;

import java.time.LocalDate;

import MAG.MAG_system.Model.TaskType;
import lombok.Builder;

@Builder
public record TaskGetDTO(

    String name,
    String description,
    TaskType type,
    Integer score,
    LocalDate created_date,
    LocalDate expired_date


) {

}
