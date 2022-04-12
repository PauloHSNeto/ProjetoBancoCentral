package ibm.itau.projetobancocentral.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DadosService {

    @Autowired
    private RestTemplate restTemplate;
    private String urlAPI = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.4505/dados?formato=json";

    public String getDados() {
        return restTemplate.getForObject(urlAPI, String.class);
    }


}