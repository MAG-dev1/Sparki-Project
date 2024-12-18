package User.micro_sevice.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import User.micro_sevice.DTO.AuthDTO;
import User.micro_sevice.DTO.CreateUserDTO;
import User.micro_sevice.Model.User;
import User.micro_sevice.Service.UserService;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import User.micro_sevice.Service.JwtService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

             

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    public ResponseEntity<?> getUser(@PathVariable Long id) throws UserPrincipalNotFoundException {
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }
    
    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO user) {
        
        User userSaved = userService.createUser(user);
        return ResponseEntity.ok(userSaved);
    }
    

    
}
