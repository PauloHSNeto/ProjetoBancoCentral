package ibm.itau.projetobancocentral.AppConfig;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class AppConfig implements CommandLineRunner {
    @Autowired
    private DadosRepository dadosRepository;
    @Autowired
    public RestTemplate restTemplate;

    @Value("${banco-central-api-url}")
    String url;

    @Override
    public void run(String... args) {

        Dados[] arraysDeDados = restTemplate.getForObject(url, Dados[].class);

        for (Dados d : arraysDeDados)  dadosRepository.save(d);


    }
}

