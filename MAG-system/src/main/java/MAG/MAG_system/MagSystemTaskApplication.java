package MAG.MAG_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class MagSystemTaskApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(MagSystemTaskApplication.class, args);
	}

}
