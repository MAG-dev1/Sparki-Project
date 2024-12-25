package User.micro_sevice.Service;

import User.micro_sevice.DTO.CreateUserDTO;
import User.micro_sevice.DTO.EditUserDTO;
import User.micro_sevice.DTO.GetUserDTO;
import User.micro_sevice.Mapper.UserMapper;
import User.micro_sevice.Model.Rol;
import User.micro_sevice.Model.User;
import User.micro_sevice.Repository.RolRepository;
import User.micro_sevice.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean UserMapper userkMapper(){
        return new UserMapper();
    }

    public User getUser(Long id) throws UserPrincipalNotFoundException {
        return userRepository.findById(id)
        .orElseThrow(() -> new UserPrincipalNotFoundException("no se ha encontrado al user de id: " + id));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) throws UserPrincipalNotFoundException{
        return userRepository.findByUsername(username)
        .orElseThrow(() -> new UserPrincipalNotFoundException("no se ha encontrado al user de username: " + username));
    }


    public GetUserDTO createUser(CreateUserDTO user) throws BadRequestException{

        Rol newRole = Rol.builder().name(user.rol().toString()).build();
        Rol rol = rolRepository.findByName(user.rol().name()).orElse(null);

        if (rol == null) 
            rol = rolRepository.save(newRole);
        
        User userSaved = User
        .builder()
        .name(user.name())
        .username(user.username())
        .email(user.email())
        .password(encoder().encode(user.password()))
        .rol(rol)
        .build();

        userRepository.save(userSaved);
        return userkMapper().toDTO(userSaved);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
       User user = getUserSaved(username);
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRol().getName());


       return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword(),
        Collections.singleton(authority)
    );
    }

    public EditUserDTO editUser(String username, EditUserDTO user) throws Exception{
        User userSaved = getUserSaved(username);

        userSaved.setName(user.name());
        userSaved.setPassword(encoder().encode(user.password()));
        userSaved.setUsername(user.username());
        userSaved.setEmail(user.email());

        userRepository.save(userSaved);

        return user;
    }

    @Transactional
    public void deleteUser(String username) {
        User userSaved = getUserSaved(username);
        userRepository.deleteById(userSaved.getId());
    }

    private User getUserSaved(String username) {
        User userSaved = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        return userSaved;
    }


}
