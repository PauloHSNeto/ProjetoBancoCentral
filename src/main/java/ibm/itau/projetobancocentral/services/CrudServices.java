package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class CrudServices {

    @Autowired
    private final DadosRepository dadosRepository;
    @Autowired
    private ValueServices valueServices;

    public List<Dados> getAllDados() {return dadosRepository.findAll();}

    public Dados findById(Long id) {return dadosRepository.findById(id).get();}

    public Dados save(Dados dado) {
        dadosRepository.save(dado);
        valueServices.updateDifference();
        return dado;}


    public void deleteById(Long id) {
        dadosRepository.deleteById(id);
        valueServices.updateDifference();
    }

    public Dados update(Long id, Dados dadoRecebido) {
        Dados dadosSalvo = dadosRepository.findById(id).get();
        dadosSalvo.setData(dadoRecebido.getData());
        dadosSalvo.setValor(dadoRecebido.getValor());
        dadosRepository.save(dadosSalvo);
        valueServices.updateDifference();
        return dadosSalvo;
    }
}