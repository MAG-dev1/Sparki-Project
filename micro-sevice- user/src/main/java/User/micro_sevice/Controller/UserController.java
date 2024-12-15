package User.micro_sevice.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import User.micro_sevice.Model.User;
import User.micro_sevice.Service.UserService;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) throws UserPrincipalNotFoundException {
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }
    
    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        
        User userSaved = userService.saveUser(user);
        return ResponseEntity.ok(userSaved);
    }
    

}
