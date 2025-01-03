package MAG.MAG_system.Factory;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import MAG.MAG_system.DTO.SubjectCreatedDTO;
import MAG.MAG_system.Model.Subject;
import MAG.MAG_system.Model.SubjectStatus;
import jakarta.ws.rs.BadRequestException;

@Component
public class SubjectFactory {
    
    
    public Subject createSubject(SubjectCreatedDTO subject, Long id) throws Exception {

        if(subject.status().equals(SubjectStatus.APPROVED) && subject.grade() == null)
            throw new BadRequestException("grade is required if subject is approved");
             
        return Subject
                .builder()
                .idUser(id)
                .username(subject.username())
                .name(subject.name())
                .description(subject.description())
                .semester(subject.semester())
                .created_date(LocalDate.now())
                .status(subject.status())
                .grade(subject.grade())
                .build();
    }

}
