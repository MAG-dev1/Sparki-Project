package User.micro_sevice.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.bouncycastle.crypto.generators.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import User.micro_sevice.DTO.CreateUserDTO;
import User.micro_sevice.Model.Rol;
import User.micro_sevice.Model.User;
import User.micro_sevice.Repository.RolRepository;
import User.micro_sevice.Repository.UserRepository;

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

    public User getUser(Long id) throws UserPrincipalNotFoundException {
        return userRepository.findById(id)
        .orElseThrow(() -> new UserPrincipalNotFoundException("no se ha encontrado al user de id: " + id));
    }


    public User createUser(CreateUserDTO user){

        Rol role = Rol.builder().name(user.rol()).build();

        boolean present = rolRepository.findByName(role.getName()).isPresent();

        if (!present) {
            rolRepository.save(role);
        }

        User userSaved = User
        .builder()
        .name(user.name())
        .username(user.username())
        .email(user.email())
        .password(encoder().encode(user.password()))
        .rol(role)
        .build();

        userRepository.save(userSaved);
        return userSaved;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
       User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));

        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRol().getName());


       return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword(),
        Collections.singleton(authority)
    );
    }
}
