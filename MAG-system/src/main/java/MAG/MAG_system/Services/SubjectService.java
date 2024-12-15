package MAG.MAG_system.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import MAG.MAG_system.DTO.SubjectCreatedDTO;
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

    public List<Subject> getAllSubjects(Long idUser) {
        idUserExists(idUser);
        return subjectRepository.findByIdUser(idUser);
    }

    public Subject createSubject(SubjectCreatedDTO subject) {

        Long idUser = subject.idUser();
        idUserExists(idUser);

        Subject sub = Subject
                .builder()
                .idUser(idUser)
                .name(subject.name())
                .description(subject.description())
                .semester(subject.semester())
                .created_date(LocalDate.now())
                .status(subject.status())
                .grade(subject.grade())
                .build();

        subjectRepository.save(sub);
        return sub;
    }

    public Subject editSubjectByName(String name, SubjectCreatedDTO subjectDTO) {

        Subject subject = subjectRepository
                .findByName(subjectDTO.name())
                .orElseThrow(() -> new SubjectNotFoundException("subject not found"));

        subject.setDescription(subjectDTO.description());
        subject.setSemester(subjectDTO.semester());
        subject.setStatus(subjectDTO.status());
        subject.setGrade(subjectDTO.grade());

        subjectRepository.save(subject);
        return subject;
    }

    @Transactional
    public void deleteSubjectByName(String name) {
        subjectRepository.deleteByName(name);
    }

    private User idUserExists(Long idUser) {
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

    public Optional<Subject> existsByName(String name) {
        return subjectRepository.findByName(name);
    }
}
