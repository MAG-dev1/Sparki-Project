package MAG.MAG_system.Services;

import java.nio.channels.Channel;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import MAG.MAG_system.Exception.UnathorizedException;
import io.netty.channel.ChannelOption;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

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
                .clientConnector(new ReactorClientHttpConnector(
                    HttpClient
                    .create()
                    .responseTimeout(Duration.ofSeconds(60))
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60000)))
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

}
