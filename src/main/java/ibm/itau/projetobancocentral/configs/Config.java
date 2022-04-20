package ibm.itau.projetobancocentral.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class Config {

    @Bean
    @Profile("test")
    public EnviromentConfig forTest(){
         return new EnviromentConfig("I`m a test");
    }
    @Bean
    @Profile("prod")
    public EnviromentConfig forProd(){
        return new EnviromentConfig("I`m in production");
    }
}
