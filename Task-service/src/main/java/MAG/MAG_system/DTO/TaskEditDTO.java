package MAG.MAG_system.DTO;

import MAG.MAG_system.Model.TaskType;

public record TaskEditDTO(
        String name,
        String description,
        TaskType type,
        String semester,
        Integer importance
) {

}
