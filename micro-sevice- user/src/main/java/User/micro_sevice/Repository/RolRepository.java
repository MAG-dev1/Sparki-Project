package User.micro_sevice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import User.micro_sevice.Model.Rol;
import java.util.Optional;


public interface RolRepository extends JpaRepository<Rol, Long>{
    Optional<Rol> findByName(String name);
}
