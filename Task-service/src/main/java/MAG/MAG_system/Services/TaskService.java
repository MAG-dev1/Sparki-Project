package MAG.MAG_system.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MAG.MAG_system.Component.TaskMapper;
import MAG.MAG_system.DTO.SubjectGetDTO;
import MAG.MAG_system.DTO.TaskCreateDTO;
import MAG.MAG_system.DTO.TaskEditDTO;
import MAG.MAG_system.DTO.TaskGetDTO;
import MAG.MAG_system.Exception.SubjectNotFoundException;
import MAG.MAG_system.Exception.TaskNotFoundException;
import MAG.MAG_system.Model.Subject;
import MAG.MAG_system.Model.Task;
import MAG.MAG_system.Repository.TaskRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MAG.MAG_system.Component.TaskMapper taskMapper;

    public List<TaskGetDTO> getAllTasks() {
        
        List<TaskGetDTO> tasks = new ArrayList<>();
        for (Task task : taskRepository.findAll()) {
            tasks.add(taskMapper.toGetDTO(task));
        }
      
        return tasks;
    }

    public List<TaskGetDTO> getAllTasksBySubjectName(String name) {

        Subject subject = subjectService
        .existsByName(name)
        .orElseThrow(()-> new SubjectNotFoundException("No existe tal materia"));

        List<TaskGetDTO> tasks = new ArrayList<>();

        subject.getTasks().stream().forEach((t) -> tasks.add(taskMapper.toGetDTO(t)));
      
        return tasks;
    }


    public TaskGetDTO associateTaskToSubject(String name_subject, TaskCreateDTO task, String token) throws Exception {
        
        tokenService.hasAuthorization(token);
        Subject sub = subjectService.existsByName(name_subject).orElseThrow(() -> new SubjectNotFoundException("Subject is invalid"));
       
        if (sub.getTasks().stream()
            .anyMatch(t -> t.getName().equals(task.name()))) throw new IllegalArgumentException("task exists!");

        Task taskCreated = TaskMapper.buildTask(task, sub);
        
        sub.getTasks().add(taskCreated);
        taskRepository.save(taskCreated);
        return taskMapper.toGetDTO(taskCreated);


    }

    
    public TaskGetDTO editTask(String taskName, String subjectName, TaskEditDTO task, String token) throws Exception {
        
        tokenService.hasAuthorization(token);
        Task taskRepo = taskRepository
        .findByNameAndSubject(taskName, subjectService.existsByName(subjectName).orElseThrow(() -> new SubjectNotFoundException("subject is invalid")).getIdSubject())
        .orElseThrow(() -> new TaskNotFoundException("task is invalid"));

       taskRepo = TaskMapper.edit(task, taskRepo);

        taskRepository.save(taskRepo);
        return taskMapper.toGetDTO(taskRepo);
    }
    
    @Transactional
    public TaskGetDTO deleteTask(String taskName, String token) throws Exception{
        tokenService.hasAuthorization(token);
        Task taskRepo = taskRepository.findByName(taskName).orElseThrow(() -> new BadRequestException("name is required!"));
        taskRepository.deleteById(taskRepo.getId());
        return taskMapper.toGetDTO(taskRepo);
    }

    public List<TaskGetDTO> getAllTasksByusername(String username, String token) throws Exception {
    
        ArrayList<TaskGetDTO> tasks = new ArrayList<>();
        for (Subject subject : subjectService.getAllSubjectsByUsername(username, token)) {
            if (subject.getUsername().equals(username)) {
              
                for (Task task: subject.getTasks()) 
                    tasks.add(taskMapper.toGetDTO(task));
                
            }
        }
        return tasks;
    }

   
}
