package ibm.itau.projetobancocentral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProjetoBancoCentralApplication {
    public static void main(String[] args) {SpringApplication.run(ProjetoBancoCentralApplication.class, args);}

}
