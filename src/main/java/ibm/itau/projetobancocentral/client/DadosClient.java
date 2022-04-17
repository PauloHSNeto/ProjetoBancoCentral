package ibm.itau.projetobancocentral.client;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Arrays;

@Component
@Transactional
public class DadosClient implements CommandLineRunner {
    @Autowired
    private DadosRepository dadosRepository;
    @Autowired
    public RestTemplate restTemplate;

    @Value("${banco-central-api-url}")
    String url;

    @Override
    public void run(String... args) {
        if (dadosRepository.count() == 0) { // Se não existir dados no banco, então cria
        Dados[] arraysDeDados = restTemplate.getForObject(url, Dados[].class); // Busca os dados da API
        dadosRepository.saveAll(Arrays.asList(arraysDeDados)); // Salva os dados no banco
        }
    }
}

