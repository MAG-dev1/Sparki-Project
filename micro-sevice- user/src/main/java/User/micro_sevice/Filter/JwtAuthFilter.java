package User.micro_sevice.Filter;

import java.io.IOException;
import java.nio.file.DirectoryStream.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import User.micro_sevice.Service.JwtService;
import User.micro_sevice.Service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {

      @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    
        String authHeader = request.getHeader("Authorization");


        if (authHeader == null) throw new NullPointerException("Authorization is required");

        if (authHeader.startsWith("Bearer ")) {
            
            String token = authHeader.substring(7);
            String username = jwtService.extractUsername(token);
        }
            
        
    }

  
    
}
