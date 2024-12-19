package MAG.MAG_system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import MAG.MAG_system.DTO.TaskCreateDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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

    @PostMapping("/associate/{name_subject}")
    public  ResponseEntity<?> createTask(@RequestParam String name_subject, @RequestBody TaskCreateDTO task) {
        taskService.associateTaskToSubject(name_subject, task);
        return ResponseEntity.ok("created!");
    }
}
