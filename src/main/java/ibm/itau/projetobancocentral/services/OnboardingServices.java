package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
@AllArgsConstructor
@Service
@NoArgsConstructor
public class OnboardingServices {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DadosRepository dadosRepository;
    @Autowired
    private ValueServices valueServices;


    public String onboarding(String url){


        Dados[] arraysDeDados = restTemplate.getForObject(url, Dados[].class);
        dadosRepository.saveAll(Arrays.asList(arraysDeDados));
        valueServices.updateDifference();
        return "Success!";

    }
    public void deleteOnboarding(){
        dadosRepository.deleteAll();
    }
}
