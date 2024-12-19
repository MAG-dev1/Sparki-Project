package MAG.MAG_system.DTO;

import MAG.MAG_system.Model.SubjectStatus;

public record SubjectCreatedDTO(
        String username,
        String name,
        String semester,
        String description,
        SubjectStatus status,
        Integer grade) {
}
