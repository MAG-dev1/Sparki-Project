package User.micro_sevice.Filter;

import java.io.IOException;
import java.nio.file.DirectoryStream.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import User.micro_sevice.Service.JwtService;
import User.micro_sevice.Service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.ext.ParamConverter.Lazy;

@Component
@Lazy
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    private final AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = request -> new WebAuthenticationDetails(
            request);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getRequestURI().contains("/users/generateToken")) {
            filterChain.doFilter(request, response); // No aplicar el filtro, continuar con la cadena
            return;
        }

        try {
            String authHeader = request.getHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {

                String token = authHeader.substring(7);
                String username = jwtService.extractUsername(token);

                UserDetails details = getDetails(username);

                if (jwtService.validateToken(token, details)) {

                    authenticationDetailsSource.buildDetails(request);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            details,
                            null, details.getAuthorities());
                    authentication.setDetails(authenticationDetailsSource.buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }

            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: " + e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);

    }

    private UserDetails getDetails(String username) {
        Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();

        if (existingAuth != null && existingAuth.isAuthenticated()) {
            return (UserDetails) existingAuth.getPrincipal();
        }

        return userService.loadUserByUsername(username);
    }

}
