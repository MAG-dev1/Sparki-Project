package MAG.MAG_system.Controller;

import org.springframework.web.bind.annotation.RestController;

import MAG.MAG_system.DTO.SubjectCreatedDTO;
import MAG.MAG_system.DTO.TaskCreateDTO;
import MAG.MAG_system.Model.Subject;
import MAG.MAG_system.Services.SubjectService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;


    //basic operations
    @GetMapping("/{id_user}")
    public ResponseEntity<?> getAllSubjects(@RequestParam("id_user") @Valid Long idUser) {

        List<Subject> subjects = subjectService.getAllSubjects(idUser);
        if (subjects.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(subjects);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody SubjectCreatedDTO subject) {
        Subject sub = subjectService.createSubject(subject);
        sub.setIdSubject(-1L);
        return ResponseEntity.ok(sub);
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> editByName (@RequestParam String name, @RequestBody SubjectCreatedDTO subject) {
        return ResponseEntity.ok(subjectService.editSubjectByName(name, subject));
    }
    
    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteByName(@RequestParam("name") String name){
        subjectService.deleteSubjectByName(name);
        return ResponseEntity.ok("deleted!");
    }

    //associations operations

    @PostMapping("/{name}")
    public  ResponseEntity<?> createTask(@RequestParam String name, @RequestBody TaskCreateDTO task) {
        return ResponseEntity.ok("created!");
    }
    
    
}
