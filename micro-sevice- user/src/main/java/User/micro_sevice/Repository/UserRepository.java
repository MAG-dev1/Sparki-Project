package User.micro_sevice.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import User.micro_sevice.Model.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{
    
}
