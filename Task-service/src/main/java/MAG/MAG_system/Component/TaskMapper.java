package MAG.MAG_system.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import MAG.MAG_system.DTO.TaskCreateDTO;
import MAG.MAG_system.DTO.TaskGetDTO;
import MAG.MAG_system.Model.Subject;
import MAG.MAG_system.Model.Task;

@Component
public class TaskMapper {
    
    public TaskGetDTO toGetDTO(Task t) {
        return TaskGetDTO.builder()
            .created_date(t.getCreated_date())
            .expired_date(t.getExpired_date())
            .name(t.getName())
            .description(t.getDescription())
            .score(t.getImportance())
            .type(t.getType())
            .subject(t.getSubject().getName())
            .build();
    }

    public static Task buildTask(TaskCreateDTO task, Subject sub){

        return Task.builder()
        .name(task.name())
        .subject(sub)
        .description(task.description())
        .type(task.type())
        .semester(task.semester())
        .created_date(LocalDateTime.now())
        .expired_date(LocalDateTime.now().plusDays(task.days()))
        .importance(task.importance())
        .build();
    }
}
