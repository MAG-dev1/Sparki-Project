package MAG.MAG_system.Component;

import org.springframework.stereotype.Component;

import MAG.MAG_system.DTO.SubjectGetDTO;
import MAG.MAG_system.Model.Subject;

@Component
public class SubjectMapper {

       public SubjectGetDTO toGetDTO(Subject sub){
        return SubjectGetDTO.builder()
            .name(sub.getName())
            .semester(sub.getSemester())
            .description(sub.getDescription())
            .status(sub.getStatus())
            .grade(sub.getGrade())
            .build();
    }
}
