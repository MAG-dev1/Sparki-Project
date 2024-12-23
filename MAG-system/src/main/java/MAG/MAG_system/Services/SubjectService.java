package MAG.MAG_system.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import MAG.MAG_system.Component.SubjectMapper;
import MAG.MAG_system.DTO.SubjectCreatedDTO;
import MAG.MAG_system.DTO.SubjectGetDTO;
import MAG.MAG_system.Exception.SubjectNotFoundException;
import MAG.MAG_system.Model.Subject;
import MAG.MAG_system.Repository.SubjectRepository;
import jakarta.transaction.Transactional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SubjectMapper subjectMapper;

    public List<SubjectGetDTO> getAllSubjects(Long idUser, String token) throws Exception {
        tokenService.hasAuthorization(token);
        List<SubjectGetDTO> subjects = new ArrayList<>();
        for (Subject subject : subjectRepository.findByIdUser(idUser)) {
            SubjectGetDTO sub = subjectMapper.toGetDTO(subject);
            subjects.add(sub);
        }
        return subjects;
    }

    public SubjectGetDTO createSubject(SubjectCreatedDTO subject, String token) throws Exception {

    
        var id = tokenService.hasAuthorization(token);
      
        Subject sub = Subject
                .builder()
                .idUser(id)
                .name(subject.name())
                .description(subject.description())
                .semester(subject.semester())
                .created_date(LocalDate.now())
                .status(subject.status())
                .grade(subject.grade())
                .build();

        subjectRepository.save(sub);
        return subjectMapper.toGetDTO(sub);
    }

    public SubjectGetDTO editSubjectByName(String name, SubjectCreatedDTO subjectDTO, String token) throws Exception {
        tokenService.hasAuthorization(token);
        Subject subject = subjectRepository
                .findByName(subjectDTO.name())
                .orElseThrow(() -> new SubjectNotFoundException("subject not found"));

        subject.setDescription(subjectDTO.description());
        subject.setSemester(subjectDTO.semester());
        subject.setStatus(subjectDTO.status());
        subject.setGrade(subjectDTO.grade());

        subjectRepository.save(subject);
        return subjectMapper.toGetDTO(subject);
    }

    @Transactional
    public void deleteSubjectByName(String name, String token) {
        subjectRepository.deleteByName(name);
    }

    public Optional<Subject> existsByName(String name) {
        return subjectRepository.findByName(name);
    }

    
}
