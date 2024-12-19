package User.micro_sevice.Controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import User.micro_sevice.DTO.AuthDTO;
import User.micro_sevice.Service.JwtService;
import User.micro_sevice.Service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody AuthDTO authRequest) throws Exception {

        System.out.println(authRequest.username());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password()));

            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(authRequest.username());
            } 
            
            throw new UsernameNotFoundException("Invalid user request!");
            
        } catch (Exception e) {
          
            System.out.println(e.getMessage());
        }
        return "";

    }

    @PostMapping("/authorize")
    public ResponseEntity<?> authorize(@RequestHeader("Authorization") String tokenHeader) throws UserPrincipalNotFoundException{

        String token = tokenHeader.replace("Bearer ", "");
        if(jwtService.isTokenExpired(token)) throw new RuntimeException("token expired!");
        String username = jwtService.extractUsername(token);
        
        return ResponseEntity.ok(userService.getUserByUsername(username).getId());
    }
}
