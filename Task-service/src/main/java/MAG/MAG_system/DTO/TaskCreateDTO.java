package MAG.MAG_system.DTO;

import MAG.MAG_system.Model.TaskType;

public record TaskCreateDTO(
        String name,
        String description,
        TaskType type,
        String semester,
        Long days,
        Integer score
) {

}
