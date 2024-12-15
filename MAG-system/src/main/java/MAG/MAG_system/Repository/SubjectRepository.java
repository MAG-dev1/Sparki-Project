package MAG.MAG_system.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import MAG.MAG_system.Model.Subject;
import java.util.List;
import java.util.Optional;


public interface SubjectRepository extends JpaRepository<Subject, Long>{
    List<Subject> findByIdUser(Long idUser);
    Optional<Subject> findByName(String name);
    void deleteByName(String name);
}
