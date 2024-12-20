package MAG.MAG_system.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import MAG.MAG_system.Exception.UnathorizedException;
import MAG.MAG_system.Model.User;
import reactor.core.publisher.Mono;

@Service
public class TokenService {
    

    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    @Value("${host.user.service.url}")
    private String hostUserService;

    protected Long hasAuthorization(String tokenHeader) throws Exception{
        String token = tokenHeader.replace("Bearer ", "");
        UriComponents builder = UriComponentsBuilder
                .fromUriString(hostUserService + "/auth/authorize")
                .build();
            

        return webClientBuilder
                .build()
                .post()
                .uri(builder.toUri())
                .accept(org.springframework.http.MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .onStatus(status -> status.value() == 401, response -> {
                    return Mono.error(new UnathorizedException("Access denied"));
                })
                .bodyToMono(Long.class)
                .block();
        
    }

    private User getUserData(Long idUser, String token) {
        UriComponents builder = UriComponentsBuilder
                .fromUriString(hostUserService + "/users/{id}")
                .buildAndExpand(idUser);

        Mono<User> user = webClientBuilder
                .build()
                .get()
                .uri(builder.toUri())
                .accept(org.springframework.http.MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(User.class);
        return user.block();
    }

}
