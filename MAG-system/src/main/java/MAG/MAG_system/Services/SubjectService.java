package MAG.MAG_system.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import MAG.MAG_system.Component.SubjectMapper;
import MAG.MAG_system.DTO.SubjectCreatedDTO;
import MAG.MAG_system.DTO.SubjectGetDTO;
import MAG.MAG_system.Exception.SubjectNotFoundException;
import MAG.MAG_system.Model.Subject;
import MAG.MAG_system.Repository.SubjectRepository;
import jakarta.transaction.Transactional;
import reactor.core.publisher.Mono;
import MAG.MAG_system.Model.User;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    @Value("${host.user.service.url}")
    private String hostUserService;

    @Autowired
    private SubjectMapper subjectMapper;

    public List<SubjectGetDTO> getAllSubjects(Long idUser) {

        List<SubjectGetDTO> subjects = new ArrayList<>();
        for (Subject subject : subjectRepository.findByIdUser(idUser)) {
            SubjectGetDTO sub = subjectMapper.toGetDTO(subject);
            subjects.add(sub);
        }
        return subjects;
    }

    public SubjectGetDTO createSubject(SubjectCreatedDTO subject, String token) throws Exception {

    
        var id = hasAuthorization(token);
      

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

    public SubjectGetDTO editSubjectByName(String name, SubjectCreatedDTO subjectDTO) {

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
    public void deleteSubjectByName(String name) {
        subjectRepository.deleteByName(name);
    }

    private User getUserData(Long idUser, String token) {
        UriComponents builder = UriComponentsBuilder
                .fromUriString(hostUserService + "/users/{id}")
                .buildAndExpand(idUser);

        Mono<User> user = webClientBuilder
                .build()
                .get()
                .uri(builder.toUri())
                .accept(org.springframework.http.MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(User.class);
        return user.block();
    }

    private Long hasAuthorization(String token) throws Exception{

        UriComponents builder = UriComponentsBuilder
                .fromUriString(hostUserService + "/auth/authorize")
                .build();
            

        return webClientBuilder
                .build()
                .post()
                .uri(builder.toUri())
                .accept(org.springframework.http.MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(Long.class)
                .block();
        
    }

    public Optional<Subject> existsByName(String name) {
        return subjectRepository.findByName(name);
    }

    
}
