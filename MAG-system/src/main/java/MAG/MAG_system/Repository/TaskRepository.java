package MAG.MAG_system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import MAG.MAG_system.Model.Task;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByName(String name);

    @Query("SELECT t FROM Task t WHERE t.name = :name AND t.subject.id = :subjectId")
    Optional<Task> findByNameAndSubject(@Param("name") String name, @Param("subjectId") Long subjectId);
}
