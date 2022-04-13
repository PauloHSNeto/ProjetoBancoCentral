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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Configuration
public class AppConfig implements CommandLineRunner {
    @Autowired
    private DadosRepository dadosRepository;
    @Autowired
    public RestTemplate restTemplate;

    @Value("${banco-central-api-url}")
    String url;

    @Override
    public void run(String... args)  {

        System.out.println("URL: " + url);

//        List<Map<String,String>> listadeDados = restTemplate.getForObject(url, List.class);
        Dados[] arraysDeDados= restTemplate.getForObject(url, Dados[].class);
        Arrays.toString(arraysDeDados);
        for(Dados d: arraysDeDados){
            System.out.println(d);
            dadosRepository.save(d);

        }

//
//
//
//        for (int i = 0; i < listadeDados.size(); i++) { // Loop para criacao de objetos
//
//           System .out.println(listadeDados.get(i).get("data")+listadeDados.get(i).get("valor")); // Printa os dados da API
//
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formata a data
//            LocalDate data = LocalDate.parse(listadeDados.get(i).get("data"),formatter); // Converte a data para LocalDate
//
//            BigDecimal valor = new BigDecimal(listadeDados.get(i).get("valor")); // Converte o valor para BigDecimal
//
//            dadosRepository.save(new Dados(data,valor)); // Salva os dados no banco de dados H2
//
//

        }

    }

