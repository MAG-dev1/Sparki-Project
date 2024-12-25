package User.micro_sevice.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import User.micro_sevice.DTO.CreateUserDTO;
import User.micro_sevice.DTO.EditUserDTO;
import User.micro_sevice.DTO.GetUserDTO;
import User.micro_sevice.Model.User;
import User.micro_sevice.Service.UserService;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getMethodName() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('USER_ROLE')")
    public ResponseEntity<?> getUser(@PathVariable Long id) throws UserPrincipalNotFoundException {
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }
    
    @PostMapping("")
    //@PreAuthorize("hasAuthority('ADMIN_ROLE')")
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO user) throws BadRequestException {
        
        GetUserDTO userSaved = userService.createUser(user);
        return ResponseEntity.ok(userSaved);
    }

    @PutMapping("{username}")
    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    public ResponseEntity<?> editUser(@PathVariable String username, @RequestBody EditUserDTO user) throws Exception {
        
        EditUserDTO userEdited = userService.editUser(username, user);
        return ResponseEntity.ok("ok!" + userEdited);
    }
    
    @DeleteMapping("{username}")
    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    public ResponseEntity<?> deleteUser(@PathVariable String username) throws Exception {
        
        userService.deleteUser(username);
        return ResponseEntity.ok("ok!" + username);
    }
    
}
