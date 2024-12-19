package MAG.MAG_system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import MAG.MAG_system.Model.Task;




public interface TaskRepository extends JpaRepository<Task, Long>{
    
}
