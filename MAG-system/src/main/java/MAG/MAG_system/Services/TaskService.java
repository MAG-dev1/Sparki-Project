package MAG.MAG_system.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import MAG.MAG_system.DTO.TaskGetDTO;
import MAG.MAG_system.Exception.SubjectNotFoundException;
import MAG.MAG_system.Model.Subject;
import MAG.MAG_system.Model.Task;
import MAG.MAG_system.Repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private MAG.MAG_system.Component.TaskMapper taskMapper;

    public List<TaskGetDTO> getAllTasks() {
        
        List<TaskGetDTO> tasks = new ArrayList<>();
        for (Task task : taskRepository.findAll()) {
            tasks.add(toGetDTO(task));
        }
      
        return tasks;
    }

    public List<TaskGetDTO> getAllTasksBySubjectName(String name) {

        Subject subject = subjectService
        .existsByName(name)
        .orElseThrow(()-> new SubjectNotFoundException("No existe tal materia"));

        List<TaskGetDTO> tasks = new ArrayList<>();
       
      
        return tasks;
    }

    private TaskGetDTO toGetDTO(Task t){
        return taskMapper.toGetDTO(t);
    }
    
}
