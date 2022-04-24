package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CrudServices {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private DadosRepository dadosRepository;

    public List<Dados> findAll() {
        return dadosRepository.findAll();
    }

    public Dados findById(Long id) {
        return dadosRepository.findById(id).get();
    }

    public Dados save(Map<String,Object> map) {
        LocalDate data = LocalDate.parse(map.get("data").toString(), formatter);
        double valor = (double) map.get("valor");
        Dados dadosSalvo = new Dados(data,valor);
        dadosRepository.save(dadosSalvo);
        return dadosSalvo;
    }
    public void deleteById(Long id) {
        dadosRepository.deleteById(id);
    }

    public Dados update(Long id, Map<String,Object> dado){
        Dados dadosSalvo = dadosRepository.findById(id).get();
        dadosSalvo.setData(LocalDate.parse(dado.get("data").toString(), formatter));
        dadosSalvo.setValor((Double) dado.get("valor"));
        dadosRepository.save(dadosSalvo);
        return dadosSalvo;
    }

    }




