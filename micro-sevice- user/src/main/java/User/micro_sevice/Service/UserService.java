package User.micro_sevice.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import User.micro_sevice.Model.User;
import User.micro_sevice.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public User getUser(Long id) throws UserPrincipalNotFoundException {
        return userRepository.findById(id)
        .orElseThrow(() -> new UserPrincipalNotFoundException("no se ha encontrado al user de id: " + id));
    }

    public User saveUser(User user)  {
        return userRepository.save(user);
    }
}
