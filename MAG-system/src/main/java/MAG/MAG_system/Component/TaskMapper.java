package MAG.MAG_system.Component;

import org.springframework.stereotype.Component;

import MAG.MAG_system.DTO.TaskGetDTO;
import MAG.MAG_system.Model.Task;

@Component
public class TaskMapper {
    
    public TaskGetDTO toGetDTO(Task t) {
        return TaskGetDTO.builder()
            .created_date(t.getCreated_date())
            .expired_date(t.getExpired_date())
            .name(t.getName())
            .description(t.getDescription())
            .score(t.getScore())
            .type(t.getType())
            .build();
    }

 
}
