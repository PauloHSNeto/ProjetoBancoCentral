package ibm.itau.projetobancocentral.AppConfig;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class AppConfig implements CommandLineRunner {
    @Autowired
    private DadosRepository dadosRepository;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Value("${banco-central-api-url}")
    String url;

    @Override
    public void run(String... args)  {

        System.out.println("URL: " + url);

        List<Map<String,String>> listadeDados = restTemplate().getForObject(url, List.class);

        for (int i = 0; i < listadeDados.size(); i++) {

            System.out.println(listadeDados.get(i).get("data")+listadeDados.get(i).get("valor"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(listadeDados.get(i).get("data"),formatter);

            BigDecimal valor = new BigDecimal(listadeDados.get(i).get("valor"));
            dadosRepository.save(new Dados(data,valor));


        }

    }
}
