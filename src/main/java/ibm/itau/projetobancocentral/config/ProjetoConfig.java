package ibm.itau.projetobancocentral.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProjetoConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
