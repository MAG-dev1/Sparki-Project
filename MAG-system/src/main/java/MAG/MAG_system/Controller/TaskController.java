package MAG.MAG_system.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    @Autowired
    private MAG.MAG_system.Services.TaskService TaskService;

    @GetMapping("")
    public ResponseEntity<?> getAllTasks() {
        return ResponseEntity.ok(TaskService.getAllTasks());
    }
    
}
