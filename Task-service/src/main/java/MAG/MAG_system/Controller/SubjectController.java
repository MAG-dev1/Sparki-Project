package MAG.MAG_system.Controller;

import org.springframework.web.bind.annotation.RestController;

import MAG.MAG_system.DTO.SubjectCreatedDTO;
import MAG.MAG_system.DTO.SubjectGetDTO;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;


    //basic operations
    @GetMapping("/{id_user}")
    public ResponseEntity<?> getAllSubjects(@RequestParam("id_user") @Valid Long idUser,  @RequestHeader("Authorization") String token) throws Exception {

        List<SubjectGetDTO> subjects = subjectService.getAllSubjects(idUser, token);
        if (subjects.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(subjects);
    }

    @PostMapping("")
    public ResponseEntity<?> createSubject(@RequestBody SubjectCreatedDTO subject, @RequestHeader("Authorization") String token) throws Exception {
        SubjectGetDTO sub = subjectService.createSubject(subject, token);
        return ResponseEntity.ok(sub);
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> editSubjectByName (@RequestParam String name, @RequestBody SubjectCreatedDTO subject,  @RequestHeader("Authorization") String token) throws Exception {
        return ResponseEntity.ok(subjectService.editSubjectByName(name, subject, token));
    }
    
    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteSubjectByName(@RequestParam String name,  @RequestHeader("Authorization") String token){
        subjectService.deleteSubjectByName(name, token);
        return ResponseEntity.ok("deleted!");
    }

   
    
    
}
