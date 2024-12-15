package MAG.MAG_system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import MAG.MAG_system.Model.Subject;
import MAG.MAG_system.Model.Task;
import java.util.List;




public interface TaskRepository extends JpaRepository<Task, Long>{
    
}
