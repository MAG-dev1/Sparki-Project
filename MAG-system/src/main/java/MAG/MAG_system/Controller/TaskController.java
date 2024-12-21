package MAG.MAG_system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import MAG.MAG_system.DTO.TaskCreateDTO;
import MAG.MAG_system.DTO.TaskEditDTO;
import MAG.MAG_system.DTO.TaskGetDTO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired
    private MAG.MAG_system.Services.TaskService taskService;

    @GetMapping("")
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    
     //associations operations

    @PostMapping("/associate")
    public  ResponseEntity<?> createTask(@RequestParam String name_subject, @RequestBody TaskCreateDTO task, @RequestHeader("Authorization") String token) throws Exception {
        TaskGetDTO taskGet = taskService.associateTaskToSubject(name_subject, task, token);
        return ResponseEntity.ok(taskGet);
    }

    @PatchMapping("/subjects")
    public  ResponseEntity<?> editTask(@RequestParam String taskName, @RequestParam String subjectName, @RequestBody TaskEditDTO task, @RequestHeader("Authorization") String token) throws Exception{
        TaskGetDTO taskEdited= taskService.editTask(taskName, subjectName, task, token);
        return ResponseEntity.ok(taskEdited);
    }

    @DeleteMapping("")
    public  ResponseEntity<?> deleteTask(@RequestParam String subjectName, @RequestHeader("Authorization") String token) throws Exception{
        taskService.deleteTask(subjectName, token);
        return ResponseEntity.ok("deleted!");
    }

    
}
