package MAG.MAG_system.DTO;

import MAG.MAG_system.Model.SubjectStatus;
import lombok.Builder;

@Builder
public record SubjectGetDTO(

    String name,
    String semester,
    String description,
    SubjectStatus status,
    Integer grade

) {
    
}
